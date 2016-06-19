/*
 * Copyright @ 2015 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jitsi.impl.fileaccess;

import java.io.*;

import org.jitsi.service.fileaccess.*;

/**
 * A failsafe transaction class. By failsafe we mean here that the file
 * concerned always stays in a coherent state. This class use the transactional
 * model.
 *
 * @author Benoit Pradelle
 */
public class FailSafeTransactionImpl
    implements FailSafeTransaction
{

    /**
     * Original file used by the transaction
     */
    private File file;

    /**
     * Backup file used by the transaction
     */
    private File backup;

    /**
     * Extension of a partial file
     */
    private static final String PART_EXT = ".part";

    /**
     * Extension of a backup copy
     */
    private static final String BAK_EXT = ".bak";

    /**
     * Creates a new transaction.
     *
     * @param file The file associated with this transaction
     *
     * @throws NullPointerException if the file is null
     */
    protected FailSafeTransactionImpl(File file)
        throws NullPointerException
    {
        if (file == null) {
            throw new NullPointerException("null file provided");
        }

        this.file = file;
        this.backup = null;
    }

    /**
     * Ensure that the file accessed is in a coherent state. This function is
     * useful to do a failsafe read without starting a transaction.
     *
     * @throws IllegalStateException if the file doesn't exists anymore
     * @throws IOException if an IOException occurs during the file restoration
     */
    public void restoreFile()
        throws IllegalStateException, IOException
    {
        File back = new File(this.file.getAbsolutePath() + BAK_EXT);

        // if a backup copy is still present, simply restore it
        if (back.exists()) {
            failsafeCopy(back.getAbsolutePath(),
                    this.file.getAbsolutePath());

            back.delete();
        }
    }

    /**
     * Begins a new transaction. If a transaction is already active, commits the
     * changes and begin a new transaction.
     * A transaction can be closed by a commit or rollback operation.
     * When the transaction begins, the file is restored to a coherent state if
     * needed.
     *
     * @throws IllegalStateException if the file doesn't exists anymore
     * @throws IOException if an IOException occurs during the transaction
     * creation
     */
    public void beginTransaction()
        throws IllegalStateException, IOException
    {
        // if the last transaction hasn't been closed, commit it
        if (this.backup != null) {
            this.commit();
        }

        // if needed, restore the file in its previous state
        restoreFile();

        this.backup = new File(this.file.getAbsolutePath() + BAK_EXT);

        // else backup the current file
        failsafeCopy(this.file.getAbsolutePath(),
                this.backup.getAbsolutePath());
    }

    /**
     * Closes the transaction and commit the changes. Everything written in the
     * file during the transaction is saved.
     *
     * @throws IllegalStateException if the file doesn't exists anymore
     * @throws IOException if an IOException occurs during the operation
     */
    public void commit()
        throws IllegalStateException, IOException
    {
        if (this.backup == null) {
            return;
        }

        // simply delete the backup file
        this.backup.delete();
        this.backup = null;
    }

    /**
     * Closes the transaction and cancels the changes. Everything written in the
     * file during the transaction is NOT saved.
     * @throws IllegalStateException if the file doesn't exists anymore
     * @throws IOException if an IOException occurs during the operation
     */
    public void rollback()
        throws IllegalStateException, IOException
    {
        if (this.backup == null)
        {
            return;
        }

        // restore the backup and delete it
        failsafeCopy(this.backup.getAbsolutePath(),
                this.file.getAbsolutePath());
        this.backup.delete();
        this.backup = null;
    }

    /**
     * Copy a file in a fail-safe way. The destination is created in an atomic
     * way.
     *
     * @param from The file to copy
     * @param to The copy to create
     *
     * @throws IllegalStateException if the file doesn't exists anymore
     * @throws IOException if an IOException occurs during the operation
     */
    private void failsafeCopy(String from, String to)
        throws IllegalStateException, IOException
    {
        FileInputStream in = null;
        FileOutputStream out = null;

        // to ensure a perfect copy, delete the destination if it exists
        File toF = new File(to);
        if (toF.exists())
        {
            if (!toF.delete())
                throw new IOException("Failed to delete destination file: "
                                      + toF.getName());
        }

        File ptoF = new File(to + PART_EXT);
        if (ptoF.exists())
        {
            if (!ptoF.delete())
                throw new IOException("Failed to delete partial file: "
                                      + ptoF.getName());
        }

        try
        {
            in = new FileInputStream(from);
            out = new FileOutputStream(to + PART_EXT);
        } catch (FileNotFoundException e)
        {
            throw new IllegalStateException(e.getMessage());
        }

        // actually copy the file
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0)
        {
          out.write(buf, 0, len);
        }

        in.close();
        out.close();

        // once done, rename the partial file to the final copy
        if (!ptoF.renameTo(toF))
            throw new IOException("Failed to rename " + ptoF.getName() + " to"
                                  + toF.getName());
    }
}
