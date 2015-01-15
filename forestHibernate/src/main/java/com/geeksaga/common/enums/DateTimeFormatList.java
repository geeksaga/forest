package com.geeksaga.common.enums;

/**
 * @author geeksaga
 * @since 0.1
 * @version 0.1
 */
public enum DateTimeFormatList
{
    YYYYMMDD(DateTime.yyyyMMdd), //
    YYYY(DateTime.yyyy), //
    MMDD(DateTime.MMdd), //
    YYMMDD(DateTime.yyMMdd), //
    HHMM(DateTime.HHmm), //
    HHMMSS(DateTime.HHmmss), //
    HHMMSSSSS(DateTime.HHmmssSSS), //
    YYYYMMDDHHMMSS(DateTime.yyyyMMddHHmmss), //
    YYYYMMDDHHMMSSSSS(DateTime.yyyyMMddHHmmssSSS);

    private final DateTime dateTime;
    private static final String FMT_YYYYMMDD = "yyyyMMdd";
    private static final String FMT_YYYY = "yyyy";
    private static final String FMT_MMDD = "MMdd";
    private static final String FMT_YYMMDD = "yyMMdd";
    private static final String FMT_HHMM = "HHmm";
    private static final String FMT_HHMMSS = "HHmmss";
    private static final String FMT_HHMMSSSSS = "HHmmssSSS";
    private static final String FMT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    private static final String FMT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    DateTimeFormatList(DateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    private enum DateTime
    {
        yyyyMMdd
        {
            String getDateTimeFormat()
            {
                return FMT_YYYYMMDD;
            }

            boolean findTableByName(String str)
            {
                if (str != null && !"".equals(str) && FMT_YYYYMMDD.equals(str))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        },

        yyyy
        {
            String getDateTimeFormat()
            {
                return FMT_YYYY;
            }

            boolean findTableByName(String str)
            {
                if (str != null && !"".equals(str) && FMT_YYYY.equals(str))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        },

        MMdd
        {
            String getDateTimeFormat()
            {
                return FMT_MMDD;
            }

            boolean findTableByName(String str)
            {
                if (str != null && !"".equals(str) && FMT_MMDD.equals(str))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        },

        yyMMdd
        {
            String getDateTimeFormat()
            {
                return FMT_YYMMDD;
            }

            boolean findTableByName(String str)
            {
                if (str != null && !"".equals(str) && FMT_YYMMDD.equals(str))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        },

        HHmm
        {
            String getDateTimeFormat()
            {
                return FMT_HHMM;
            }

            boolean findTableByName(String str)
            {
                if (str != null && !"".equals(str) && FMT_HHMM.equals(str))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        },

        HHmmss
        {
            String getDateTimeFormat()
            {
                return FMT_HHMMSS;
            }

            boolean findTableByName(String str)
            {
                if (str != null && !"".equals(str) && FMT_HHMMSS.equals(str))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        },

        HHmmssSSS
        {
            String getDateTimeFormat()
            {
                return FMT_HHMMSSSSS;
            }

            boolean findTableByName(String str)
            {
                if (str != null && !"".equals(str) && FMT_HHMMSSSSS.equals(str))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        },

        yyyyMMddHHmmss
        {
            String getDateTimeFormat()
            {
                return FMT_YYYYMMDDHHMMSS;
            }

            boolean findTableByName(String str)
            {
                if (str != null && !"".equals(str) && FMT_YYYYMMDDHHMMSS.equals(str))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        },

        yyyyMMddHHmmssSSS
        {
            String getDateTimeFormat()
            {
                return FMT_YYYYMMDDHHMMSSSSS;
            }

            boolean findTableByName(String str)
            {
                if (str != null && !"".equals(str) && FMT_YYYYMMDDHHMMSSSSS.equals(str))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        };

        abstract String getDateTimeFormat();

        abstract boolean findTableByName(String str);
    }

    public String getDateTimeFormat()
    {
        return dateTime.getDateTimeFormat();
    }

    public DateTime getDateTime()
    {
        return dateTime;
    }

    public boolean findTableByName(String str)
    {
        return dateTime.findTableByName(str);
    }
}