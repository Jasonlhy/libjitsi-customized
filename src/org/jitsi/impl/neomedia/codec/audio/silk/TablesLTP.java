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
package org.jitsi.impl.neomedia.codec.audio.silk;

/**
 *
 * @author Jing Dai
 * @author Dingxin Xu
 */
public class TablesLTP
{
    static int[] SKP_Silk_LTP_per_index_CDF = {
             0,  20992,  40788,  65535
    };

    static final int SKP_Silk_LTP_per_index_CDF_offset = 1;

    static int[] SKP_Silk_LTP_gain_CDF_0 = {
             0,  49380,  54463,  56494,  58437,  60101,  61683,  62985,
         64066,  64823,  65535
    };

    static int[] SKP_Silk_LTP_gain_CDF_1 = {
             0,  25290,  30654,  35710,  40386,  42937,  45250,  47459,
         49411,  51348,  52974,  54517,  55976,  57423,  58865,  60285,
         61667,  62895,  63827,  64724,  65535
    };

    static int[] SKP_Silk_LTP_gain_CDF_2 = {
             0,   4958,   9439,  13581,  17638,  21651,  25015,  28025,
         30287,  32406,  34330,  36240,  38130,  39790,  41281,  42764,
         44229,  45676,  47081,  48431,  49675,  50849,  51932,  52966,
         53957,  54936,  55869,  56789,  57708,  58504,  59285,  60043,
         60796,  61542,  62218,  62871,  63483,  64076,  64583,  65062,
         65535
    };

    static  int[] SKP_Silk_LTP_gain_CDF_offsets = {
             1,     3,     10
    };

    static final int SKP_Silk_LTP_gain_middle_avg_RD_Q14 = 11010;

    static short[] SKP_Silk_LTP_gain_BITS_Q6_0 = {
            26,    236,    321,    325,    339,    344,    362,    379,
           412,    418
    };

    static short[]  SKP_Silk_LTP_gain_BITS_Q6_1 = {
            88,    231,    237,    244,    300,    309,    313,    324,
           325,    341,    346,    351,    352,    352,    354,    356,
           367,    393,    396,    406
    };

    static short[] SKP_Silk_LTP_gain_BITS_Q6_2 = {
           238,    248,    255,    257,    258,    274,    284,    311,
           317,    326,    326,    327,    339,    349,    350,    351,
           352,    355,    358,    366,    371,    379,    383,    387,
           388,    393,    394,    394,    407,    409,    412,    412,
           413,    422,    426,    432,    434,    449,    454,    455
    };

    static int[][] SKP_Silk_LTP_gain_CDF_ptrs = {
    SKP_Silk_LTP_gain_CDF_0,
    SKP_Silk_LTP_gain_CDF_1,
    SKP_Silk_LTP_gain_CDF_2
    };

    static short[][] SKP_Silk_LTP_gain_BITS_Q6_ptrs = {
        SKP_Silk_LTP_gain_BITS_Q6_0,
        SKP_Silk_LTP_gain_BITS_Q6_1,
        SKP_Silk_LTP_gain_BITS_Q6_2
    };

    static short[][] SKP_Silk_LTP_gain_vq_0_Q14 =
    {
    {
           594,    984,   2840,   1021,    669
    },
    {
            10,     35,    304,     -1,     23
    },
    {
          -694,   1923,   4603,   2975,   2335
    },
    {
          2437,   3176,   3778,   1940,    481
    },
    {
           214,    -46,   7870,   4406,   -521
    },
    {
          -896,   4818,   8501,   1623,   -887
    },
    {
          -696,   3178,   6480,   -302,   1081
    },
    {
           517,    599,   1002,    567,    560
    },
    {
         -2075,   -834,   4712,   -340,    896
    },
    {
          1435,   -644,   3993,   -612,  -2063
    }
    };

    static short[][]  SKP_Silk_LTP_gain_vq_1_Q14 =
    {
    {
          1655,   2918,   5001,   3010,   1775
    },
    {
           113,    198,    856,    176,    178
    },
    {
          -843,   2479,   7858,   5371,    574
    },
    {
            59,   5356,   7648,   2850,   -315
    },
    {
          3840,   4851,   6527,   1583,  -1233
    },
    {
          1620,   1760,   2330,   1876,   2045
    },
    {
          -545,   1854,  11792,   1547,   -307
    },
    {
          -604,    689,   5369,   5074,   4265
    },
    {
           521,  -1331,   9829,   6209,  -1211
    },
    {
         -1315,   6747,   9929,  -1410,    546
    },
    {
           117,   -144,   2810,   1649,   5240
    },
    {
          5392,   3476,   2425,    -38,    633
    },
    {
            14,   -449,   5274,   3547,   -171
    },
    {
           -98,    395,   9114,   1676,    844
    },
    {
          -908,   3843,   8861,   -957,   1474
    },
    {
           396,   6747,   5379,   -329,   1269
    },
    {
          -335,   2830,   4281,    270,    -54
    },
    {
          1502,   5609,   8958,   6045,   2059
    },
    {
          -370,    479,   5267,   5726,   1174
    },
    {
          5237,  -1144,   6510,    455,    512
    }
    };

    static short[][] SKP_Silk_LTP_gain_vq_2_Q14 =
    {
    {
          -278,    415,   9345,   7106,   -431
    },
    {
         -1006,   3863,   9524,   4724,   -871
    },
    {
          -954,   4624,  11722,    973,   -300
    },
    {
          -117,   7066,   8331,   1959,   -901
    },
    {
           593,   3412,   6070,   4914,   1567
    },
    {
            54,    -51,  12618,   4228,   -844
    },
    {
          3157,   4822,   5229,   2313,    717
    },
    {
          -244,   1161,  14198,    779,     69
    },
    {
         -1218,   5603,  12894,  -2301,   1001
    },
    {
          -132,   3960,   9526,    577,   1806
    },
    {
         -1633,   8815,  10484,  -2452,    895
    },
    {
           235,    450,   1243,    667,    437
    },
    {
           959,  -2630,  10897,   8772,  -1852
    },
    {
          2420,   2046,   8893,   4427,  -1569
    },
    {
            23,   7091,   8356,  -1285,   1508
    },
    {
         -1133,    835,   7662,   6043,   2800
    },
    {
           439,    391,  11016,   2253,   1362
    },
    {
         -1020,   2876,  13436,   4015,  -3020
    },
    {
          1060,  -2690,  13512,   5565,  -1394
    },
    {
         -1420,   8007,  11421,   -152,  -1672
    },
    {
          -893,   2895,  15434,  -1490,    159
    },
    {
         -1054,    428,  12208,   8538,  -3344
    },
    {
          1772,  -1304,   7593,   6185,    561
    },
    {
           525,  -1207,   6659,  11151,  -1170
    },
    {
           439,   2667,   4743,   2359,   5515
    },
    {
          2951,   7432,   7909,   -230,  -1564
    },
    {
           -72,   2140,   5477,   1391,   1580
    },
    {
           476,  -1312,  15912,   2174,  -1027
    },
    {
          5737,    441,   2493,   2043,   2757
    },
    {
           228,    -43,   1803,   6663,   7064
    },
    {
          4596,   9182,   1917,   -200,    203
    },
    {
          -704,  12039,   5451,  -1188,    542
    },
    {
          1782,  -1040,  10078,   7513,  -2767
    },
    {
         -2626,   7747,   9019,     62,   1710
    },
    {
           235,   -233,   2954,  10921,   1947
    },
    {
         10854,   2814,   1232,   -111,    222
    },
    {
          2267,   2778,  12325,    156,  -1658
    },
    {
         -2950,   8095,  16330,    268,  -3626
    },
    {
            67,   2083,   7950,    -80,  -2432
    },
    {
           518,    -66,   1718,    415,  11435
    }
    };

    private static final short[] SKP_Silk_LTP_gain_vq_0_Q14_00 =
        {
//        {
               594,    984,   2840,   1021,    669,
//        },
//        {
                10,     35,    304,     -1,     23,
//        },
//        {
              -694,   1923,   4603,   2975,   2335,
//        },
//        {
              2437,   3176,   3778,   1940,    481,
//        },
//        {
               214,    -46,   7870,   4406,   -521,
//        },
//        {
              -896,   4818,   8501,   1623,   -887,
//        },
//        {
              -696,   3178,   6480,   -302,   1081,
//        },
//        {
               517,    599,   1002,    567,    560,
//        },
//        {
             -2075,   -834,   4712,   -340,    896,
//        },
//        {
              1435,   -644,   3993,   -612,  -2063
//        }
        };
    private static final short[] SKP_Silk_LTP_gain_vq_1_Q14_00 =
    {
//        {
              1655,   2918,   5001,   3010,   1775,
//        },
//        {
               113,    198,    856,    176,    178,
//        },
//        {
              -843,   2479,   7858,   5371,    574,
//        },
//        {
                59,   5356,   7648,   2850,   -315,
//        },
//        {
              3840,   4851,   6527,   1583,  -1233,
//        },
//        {
              1620,   1760,   2330,   1876,   2045,
//        },
//        {
              -545,   1854,  11792,   1547,   -307,
//        },
//        {
              -604,    689,   5369,   5074,   4265,
//        },
//        {
               521,  -1331,   9829,   6209,  -1211,
//        },
//        {
             -1315,   6747,   9929,  -1410,    546,
//        },
//        {
               117,   -144,   2810,   1649,   5240,
//        },
//        {
              5392,   3476,   2425,    -38,    633,
//        },
//        {
                14,   -449,   5274,   3547,   -171,
//        },
//        {
               -98,    395,   9114,   1676,    844,
//        },
//        {
              -908,   3843,   8861,   -957,   1474,
//        },
//        {
               396,   6747,   5379,   -329,   1269,
//        },
//        {
              -335,   2830,   4281,    270,    -54,
//        },
//        {
              1502,   5609,   8958,   6045,   2059,
//        },
//        {
              -370,    479,   5267,   5726,   1174,
//        },
//        {
              5237,  -1144,   6510,    455,    512
//        }
    };
    private static final short[] SKP_Silk_LTP_gain_vq_2_Q14_00 =
    {
//        {
              -278,    415,   9345,   7106,   -431,
//        },
//        {
             -1006,   3863,   9524,   4724,   -871,
//        },
//        {
              -954,   4624,  11722,    973,   -300,
//        },
//        {
              -117,   7066,   8331,   1959,   -901,
//        },
//        {
               593,   3412,   6070,   4914,   1567,
//        },
//        {
                54,    -51,  12618,   4228,   -844,
//        },
//        {
              3157,   4822,   5229,   2313,    717,
//        },
//        {
              -244,   1161,  14198,    779,     69,
//        },
//        {
             -1218,   5603,  12894,  -2301,   1001,
//        },
//        {
              -132,   3960,   9526,    577,   1806,
//        },
//        {
             -1633,   8815,  10484,  -2452,    895,
//        },
//        {
               235,    450,   1243,    667,    437,
//        },
//        {
               959,  -2630,  10897,   8772,  -1852,
//        },
//        {
              2420,   2046,   8893,   4427,  -1569,
//        },
//        {
                23,   7091,   8356,  -1285,   1508,
//        },
//        {
             -1133,    835,   7662,   6043,   2800,
//        },
//        {
               439,    391,  11016,   2253,   1362,
//        },
//        {
             -1020,   2876,  13436,   4015,  -3020,
//        },
//        {
              1060,  -2690,  13512,   5565,  -1394,
//        },
//        {
             -1420,   8007,  11421,   -152,  -1672,
//        },
//        {
              -893,   2895,  15434,  -1490,    159,
//        },
//        {
             -1054,    428,  12208,   8538,  -3344,
//        },
//        {
              1772,  -1304,   7593,   6185,    561,
//        },
//        {
               525,  -1207,   6659,  11151,  -1170,
//        },
//        {
               439,   2667,   4743,   2359,   5515,
//        },
//        {
              2951,   7432,   7909,   -230,  -1564,
//        },
//        {
               -72,   2140,   5477,   1391,   1580,
//        },
//        {
               476,  -1312,  15912,   2174,  -1027,
//        },
//        {
              5737,    441,   2493,   2043,   2757,
//        },
//        {
               228,    -43,   1803,   6663,   7064,
//        },
//        {
              4596,   9182,   1917,   -200,    203,
//        },
//        {
              -704,  12039,   5451,  -1188,    542,
//        },
//        {
              1782,  -1040,  10078,   7513,  -2767,
//        },
//        {
             -2626,   7747,   9019,     62,   1710,
//        },
//        {
               235,   -233,   2954,  10921,   1947,
//        },
//        {
             10854,   2814,   1232,   -111,    222,
//        },
//        {
              2267,   2778,  12325,    156,  -1658,
//        },
//        {
             -2950,   8095,  16330,    268,  -3626,
//        },
//        {
                67,   2083,   7950,    -80,  -2432,
//        },
//        {
               518,    -66,   1718,    415,  11435
//        }
    };

    static short[][] SKP_Silk_LTP_vq_ptrs_Q14 = {
        SKP_Silk_LTP_gain_vq_0_Q14_00,
        SKP_Silk_LTP_gain_vq_1_Q14_00,
        SKP_Silk_LTP_gain_vq_2_Q14_00
    };

    static int[] SKP_Silk_LTP_vq_sizes = {
        10, 20, 40
    };
}
