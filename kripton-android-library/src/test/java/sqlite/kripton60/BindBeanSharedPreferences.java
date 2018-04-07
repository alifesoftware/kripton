/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton60;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CalendarUtils;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.CurrencyUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.LocaleUtils;
import com.abubusoft.kripton.common.SQLTimeUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.TimeZoneUtils;
import com.abubusoft.kripton.common.UrlUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

// TODO: Auto-generated Javadoc
/**
 * This class is the shared preference binder defined for Bean.
 *
 * @see Bean
 */
public class BindBeanSharedPreferences extends AbstractSharedPreference {
  
  /** instance of shared preferences. */
  private static BindBeanSharedPreferences instance;

  /** working instance of bean. */
  private final Bean defaultBean;

  /** BeanBindMap. */
  private BeanBindMap beanBindMap = BinderUtils.mapperFor(Bean.class);

  /**
   * constructor.
   */
  private BindBeanSharedPreferences() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new Bean();
  }

  /**
   * create an editor to modify shared preferences.
   *
   * @return the bind editor
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * force to refresh values.
   *
   * @return the bind bean shared preferences
   */
  public BindBeanSharedPreferences refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    return this;
  }

  /**
   * reset shared preferences.
   */
  public void reset() {
    Bean bean=new Bean();
    write(bean);
  }

  /**
   * read bean entirely.
   *
   * @return read bean
   */
  public Bean read() {
    Bean bean=new Bean();
    bean.valueBoolType=(boolean)prefs.getBoolean("value_bool_type", (boolean)bean.valueBoolType);
    bean.valueBool=(boolean)prefs.getBoolean("value_bool", (boolean)(bean.valueBool==null?false:bean.valueBool));
    bean.valueByteType=(byte)prefs.getInt("value_byte_type", (byte)bean.valueByteType);
    bean.valueByte=(byte)prefs.getInt("value_byte", (byte)(bean.valueByte==null?(byte)0:bean.valueByte));
    bean.valueShortType=(short)prefs.getInt("value_short_type", (short)bean.valueShortType);
    bean.valueShort=(short)prefs.getInt("value_short", (short)(bean.valueShort==null?(short)0:bean.valueShort));
    bean.valueIntType=(int)prefs.getInt("value_int_type", (int)bean.valueIntType);
    bean.valueInt=(int)prefs.getInt("value_int", (int)(bean.valueInt==null?0:bean.valueInt));
    bean.valueString=prefs.getString("value_string", bean.valueString);
    bean.valueCharType=(char)prefs.getInt("value_char_type", (char)bean.valueCharType);
    bean.valueChar=(char)prefs.getInt("value_char", (char)(bean.valueChar==null?(char)0:bean.valueChar));
    bean.valueFloatType=prefs.getFloat("value_float_type", bean.valueFloatType);
    bean.valueFloat=prefs.getFloat("value_float", (bean.valueFloat==null?0F:bean.valueFloat));
     {
      String temp=prefs.getString("value_big_integer", "0");
      bean.valueBigInteger=(StringUtils.hasText(temp)) ? new BigInteger(temp): null;
    }

     {
      String temp=prefs.getString("value_big_decimal", "0");
      bean.valueBigDecimal=(StringUtils.hasText(temp)) ? new BigDecimal(temp): null;
    }

     {
      String temp=prefs.getString("value_enum_type", null);
      bean.valueEnumType=(StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null;
    }

    bean.valueLongType=prefs.getLong("value_long_type", bean.valueLongType);
    bean.valueLong=prefs.getLong("value_long", (bean.valueLong==null?0L:bean.valueLong));
     {
      String temp=prefs.getString("value_double_type", null);
      bean.valueDoubleType=(StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;
    }

     {
      String temp=prefs.getString("value_double", null);
      bean.valueDouble=(StringUtils.hasText(temp)) ? Double.valueOf(temp): null;
    }

     {
      String temp=prefs.getString("value_locale", null);
      bean.valueLocale=(StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_calendar", null);
      bean.valueCalendar=(StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_date", null);
      bean.valueDate=(StringUtils.hasText(temp)) ? DateUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_url", null);
      bean.valueUrl=(StringUtils.hasText(temp)) ? UrlUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_time", null);
      bean.valueTime=(StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_currency", null);
      bean.valueCurrency=(StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_time_zone", null);
      bean.valueTimeZone=(StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;}

     {
      String temp=prefs.getString("value_time_list", null);
      bean.valueTimeList=StringUtils.hasText(temp) ? parseValueTimeList(temp): null;
    }

     {
      String temp=prefs.getString("value_strin_list", null);
      bean.valueStrinList=StringUtils.hasText(temp) ? parseValueStrinList(temp): null;
    }

     {
      String temp=prefs.getString("value_long_list", null);
      bean.valueLongList=StringUtils.hasText(temp) ? parseValueLongList(temp): null;
    }

     {
      String temp=prefs.getString("value_byte_array", null);
      bean.valueByteArray=StringUtils.hasText(temp) ? parseValueByteArray(temp): null;
    }

     {
      String temp=prefs.getString("value_bean", null);
      bean.valueBean=StringUtils.hasText(temp) ? parseValueBean(temp): null;
    }

     {
      String temp=prefs.getString("value_long_type_array", null);
      bean.valueLongTypeArray=StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): null;
    }

     {
      String temp=prefs.getString("value_long_array", null);
      bean.valueLongArray=StringUtils.hasText(temp) ? parseValueLongArray(temp): null;
    }

     {
      String temp=prefs.getString("value_bean_array", null);
      bean.valueBeanArray=StringUtils.hasText(temp) ? parseValueBeanArray(temp): null;
    }

     {
      String temp=prefs.getString("value_string_array", null);
      bean.valueStringArray=StringUtils.hasText(temp) ? parseValueStringArray(temp): null;
    }

     {
      String temp=prefs.getString("value_char_list", null);
      bean.valueCharList=StringUtils.hasText(temp) ? parseValueCharList(temp): null;
    }

     {
      String temp=prefs.getString("value_char_type_array", null);
      bean.valueCharTypeArray=StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): null;
    }

     {
      String temp=prefs.getString("value_char_array", null);
      bean.valueCharArray=StringUtils.hasText(temp) ? parseValueCharArray(temp): null;
    }

     {
      String temp=prefs.getString("value_map_string_bean", null);
      bean.valueMapStringBean=StringUtils.hasText(temp) ? parseValueMapStringBean(temp): null;
    }

     {
      String temp=prefs.getString("value_linked_map_string_bean", null);
      bean.valueLinkedMapStringBean=StringUtils.hasText(temp) ? parseValueLinkedMapStringBean(temp): null;
    }

     {
      Set<String> temp=prefs.getStringSet("value_set_string", defaultBean.valueSetString);
      bean.valueSetString=temp;
    }


    return bean;
  }

  /**
   * write bean entirely.
   *
   * @param bean bean to entirely write
   */
  public void write(Bean bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putBoolean("value_bool_type",(boolean)bean.valueBoolType);

    if (bean.valueBool!=null)  {
      editor.putBoolean("value_bool",(boolean)bean.valueBool);
    }

    editor.putInt("value_byte_type",(int)bean.valueByteType);

    if (bean.valueByte!=null)  {
      editor.putInt("value_byte",(int)bean.valueByte);
    }

    editor.putInt("value_short_type",(int)bean.valueShortType);

    if (bean.valueShort!=null)  {
      editor.putInt("value_short",(int)bean.valueShort);
    }

    editor.putInt("value_int_type",(int)bean.valueIntType);

    if (bean.valueInt!=null)  {
      editor.putInt("value_int",(int)bean.valueInt);
    }

    editor.putString("value_string",bean.valueString);

    editor.putInt("value_char_type",(char)bean.valueCharType);

    if (bean.valueChar!=null)  {
      editor.putInt("value_char",(char)bean.valueChar);
    }

    editor.putFloat("value_float_type",bean.valueFloatType);

    if (bean.valueFloat!=null)  {
      editor.putFloat("value_float",bean.valueFloat);
    }

    if (bean.valueBigInteger!=null) editor.putString("value_big_integer",bean.valueBigInteger.toString() ); else editor.putString("value_big_integer", null);
    if (bean.valueBigDecimal!=null) editor.putString("value_big_decimal",bean.valueBigDecimal.toPlainString() ); else editor.putString("value_big_decimal", null);
    if (bean.valueEnumType!=null)  {
      editor.putString("value_enum_type",bean.valueEnumType.toString() );
    } else {
      editor.remove("valueEnumType");
    }

    editor.putLong("value_long_type",bean.valueLongType);

    if (bean.valueLong!=null)  {
      editor.putLong("value_long",bean.valueLong);
    }

    editor.putString("value_double_type",String.valueOf(bean.valueDoubleType));

    if (bean.valueDouble!=null)  {
      editor.putString("value_double",String.valueOf(bean.valueDouble));
    } else {
      editor.remove("valueDouble");
    }

    if (bean.valueLocale!=null)  {
      editor.putString("value_locale",LocaleUtils.write(bean.valueLocale));
    } else {
      editor.remove("valueLocale");
    }

    if (bean.valueCalendar!=null)  {
      editor.putString("value_calendar",CalendarUtils.write(bean.valueCalendar));
    } else {
      editor.remove("valueCalendar");
    }

    if (bean.valueDate!=null)  {
      editor.putString("value_date",DateUtils.write(bean.valueDate));
    } else {
      editor.remove("valueDate");
    }

    if (bean.valueUrl!=null)  {
      editor.putString("value_url",UrlUtils.write(bean.valueUrl));
    } else {
      editor.remove("valueUrl");
    }

    if (bean.valueTime!=null)  {
      editor.putString("value_time",SQLTimeUtils.write(bean.valueTime));
    } else {
      editor.remove("valueTime");
    }

    if (bean.valueCurrency!=null)  {
      editor.putString("value_currency",CurrencyUtils.write(bean.valueCurrency));
    } else {
      editor.remove("valueCurrency");
    }

    if (bean.valueTimeZone!=null)  {
      editor.putString("value_time_zone",TimeZoneUtils.write(bean.valueTimeZone));
    } else {
      editor.remove("valueTimeZone");
    }

    if (bean.valueTimeList!=null)  {
      String temp=serializeValueTimeList(bean.valueTimeList);
      editor.putString("value_time_list",temp);
    }  else  {
      editor.remove("value_time_list");
    }

    if (bean.valueStrinList!=null)  {
      String temp=serializeValueStrinList(bean.valueStrinList);
      editor.putString("value_strin_list",temp);
    }  else  {
      editor.remove("value_strin_list");
    }

    if (bean.valueLongList!=null)  {
      String temp=serializeValueLongList(bean.valueLongList);
      editor.putString("value_long_list",temp);
    }  else  {
      editor.remove("value_long_list");
    }

    if (bean.valueByteArray!=null)  {
      String temp=serializeValueByteArray(bean.valueByteArray);
      editor.putString("value_byte_array",temp);
    }  else  {
      editor.remove("value_byte_array");
    }

    if (bean.valueBean!=null)  {
      String temp=serializeValueBean(bean.valueBean);
      editor.putString("value_bean",temp);
    }  else  {
      editor.remove("value_bean");
    }

    if (bean.valueLongTypeArray!=null)  {
      String temp=serializeValueLongTypeArray(bean.valueLongTypeArray);
      editor.putString("value_long_type_array",temp);
    }  else  {
      editor.remove("value_long_type_array");
    }

    if (bean.valueLongArray!=null)  {
      String temp=serializeValueLongArray(bean.valueLongArray);
      editor.putString("value_long_array",temp);
    }  else  {
      editor.remove("value_long_array");
    }

    if (bean.valueBeanArray!=null)  {
      String temp=serializeValueBeanArray(bean.valueBeanArray);
      editor.putString("value_bean_array",temp);
    }  else  {
      editor.remove("value_bean_array");
    }

    if (bean.valueStringArray!=null)  {
      String temp=serializeValueStringArray(bean.valueStringArray);
      editor.putString("value_string_array",temp);
    }  else  {
      editor.remove("value_string_array");
    }

    if (bean.valueCharList!=null)  {
      String temp=serializeValueCharList(bean.valueCharList);
      editor.putString("value_char_list",temp);
    }  else  {
      editor.remove("value_char_list");
    }

    if (bean.valueCharTypeArray!=null)  {
      String temp=serializeValueCharTypeArray(bean.valueCharTypeArray);
      editor.putString("value_char_type_array",temp);
    }  else  {
      editor.remove("value_char_type_array");
    }

    if (bean.valueCharArray!=null)  {
      String temp=serializeValueCharArray(bean.valueCharArray);
      editor.putString("value_char_array",temp);
    }  else  {
      editor.remove("value_char_array");
    }

    if (bean.valueMapStringBean!=null)  {
      String temp=serializeValueMapStringBean(bean.valueMapStringBean);
      editor.putString("value_map_string_bean",temp);
    }  else  {
      editor.remove("value_map_string_bean");
    }

    if (bean.valueLinkedMapStringBean!=null)  {
      String temp=serializeValueLinkedMapStringBean(bean.valueLinkedMapStringBean);
      editor.putString("value_linked_map_string_bean",temp);
    }  else  {
      editor.remove("value_linked_map_string_bean");
    }

    editor.putStringSet("value_set_string",bean.valueSetString);


    editor.commit();
  }

  /**
   * read property valueBoolType.
   *
   * @return property valueBoolType value
   */
  public boolean valueBoolType() {
    return (boolean)prefs.getBoolean("value_bool_type", (boolean)defaultBean.valueBoolType);
  }

  /**
   * read property valueBool.
   *
   * @return property valueBool value
   */
  public Boolean valueBool() {
    return (boolean)prefs.getBoolean("value_bool", (boolean)(defaultBean.valueBool==null?false:defaultBean.valueBool));
  }

  /**
   * read property valueByteType.
   *
   * @return property valueByteType value
   */
  public byte valueByteType() {
    return (byte)prefs.getInt("value_byte_type", (byte)defaultBean.valueByteType);
  }

  /**
   * read property valueByte.
   *
   * @return property valueByte value
   */
  public Byte valueByte() {
    return (byte)prefs.getInt("value_byte", (byte)(defaultBean.valueByte==null?(byte)0:defaultBean.valueByte));
  }

  /**
   * read property valueShortType.
   *
   * @return property valueShortType value
   */
  public short valueShortType() {
    return (short)prefs.getInt("value_short_type", (short)defaultBean.valueShortType);
  }

  /**
   * read property valueShort.
   *
   * @return property valueShort value
   */
  public Short valueShort() {
    return (short)prefs.getInt("value_short", (short)(defaultBean.valueShort==null?(short)0:defaultBean.valueShort));
  }

  /**
   * read property valueIntType.
   *
   * @return property valueIntType value
   */
  public int valueIntType() {
    return (int)prefs.getInt("value_int_type", (int)defaultBean.valueIntType);
  }

  /**
   * read property valueInt.
   *
   * @return property valueInt value
   */
  public Integer valueInt() {
    return (int)prefs.getInt("value_int", (int)(defaultBean.valueInt==null?0:defaultBean.valueInt));
  }

  /**
   * read property valueString.
   *
   * @return property valueString value
   */
  public String valueString() {
    return prefs.getString("value_string", defaultBean.valueString);
  }

  /**
   * read property valueCharType.
   *
   * @return property valueCharType value
   */
  public char valueCharType() {
    return (char)prefs.getInt("value_char_type", (char)defaultBean.valueCharType);
  }

  /**
   * read property valueChar.
   *
   * @return property valueChar value
   */
  public Character valueChar() {
    return (char)prefs.getInt("value_char", (char)(defaultBean.valueChar==null?(char)0:defaultBean.valueChar));
  }

  /**
   * read property valueFloatType.
   *
   * @return property valueFloatType value
   */
  public float valueFloatType() {
    return prefs.getFloat("value_float_type", defaultBean.valueFloatType);
  }

  /**
   * read property valueFloat.
   *
   * @return property valueFloat value
   */
  public Float valueFloat() {
    return prefs.getFloat("value_float", (defaultBean.valueFloat==null?0F:defaultBean.valueFloat));
  }

  /**
   * read property valueBigInteger.
   *
   * @return property valueBigInteger value
   */
  public BigInteger valueBigInteger() {
    String temp=prefs.getString("value_big_integer", "0");
    return (StringUtils.hasText(temp)) ? new BigInteger(temp): null;

  }

  /**
   * read property valueBigDecimal.
   *
   * @return property valueBigDecimal value
   */
  public BigDecimal valueBigDecimal() {
    String temp=prefs.getString("value_big_decimal", "0");
    return (StringUtils.hasText(temp)) ? new BigDecimal(temp): null;

  }

  /**
   * read property valueEnumType.
   *
   * @return property valueEnumType value
   */
  public EnumType valueEnumType() {
    String temp=prefs.getString("value_enum_type", null);
    return (StringUtils.hasText(temp)) ? EnumType.valueOf(temp): null;

  }

  /**
   * read property valueLongType.
   *
   * @return property valueLongType value
   */
  public long valueLongType() {
    return prefs.getLong("value_long_type", defaultBean.valueLongType);
  }

  /**
   * read property valueLong.
   *
   * @return property valueLong value
   */
  public Long valueLong() {
    return prefs.getLong("value_long", (defaultBean.valueLong==null?0L:defaultBean.valueLong));
  }

  /**
   * read property valueDoubleType.
   *
   * @return property valueDoubleType value
   */
  public double valueDoubleType() {
    String temp=prefs.getString("value_double_type", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): 0.0;

  }

  /**
   * read property valueDouble.
   *
   * @return property valueDouble value
   */
  public Double valueDouble() {
    String temp=prefs.getString("value_double", null);
    return (StringUtils.hasText(temp)) ? Double.valueOf(temp): null;

  }

  /**
   * read property valueLocale.
   *
   * @return property valueLocale value
   */
  public Locale valueLocale() {
    String temp=prefs.getString("value_locale", null);
    return (StringUtils.hasText(temp)) ? LocaleUtils.read(temp): null;
  }

  /**
   * read property valueCalendar.
   *
   * @return property valueCalendar value
   */
  public Calendar valueCalendar() {
    String temp=prefs.getString("value_calendar", null);
    return (StringUtils.hasText(temp)) ? CalendarUtils.read(temp): null;
  }

  /**
   * read property valueDate.
   *
   * @return property valueDate value
   */
  public Date valueDate() {
    String temp=prefs.getString("value_date", null);
    return (StringUtils.hasText(temp)) ? DateUtils.read(temp): null;
  }

  /**
   * read property valueUrl.
   *
   * @return property valueUrl value
   */
  public URL valueUrl() {
    String temp=prefs.getString("value_url", null);
    return (StringUtils.hasText(temp)) ? UrlUtils.read(temp): null;
  }

  /**
   * read property valueTime.
   *
   * @return property valueTime value
   */
  public Time valueTime() {
    String temp=prefs.getString("value_time", null);
    return (StringUtils.hasText(temp)) ? SQLTimeUtils.read(temp): null;
  }

  /**
   * read property valueCurrency.
   *
   * @return property valueCurrency value
   */
  public Currency valueCurrency() {
    String temp=prefs.getString("value_currency", null);
    return (StringUtils.hasText(temp)) ? CurrencyUtils.read(temp): null;
  }

  /**
   * read property valueTimeZone.
   *
   * @return property valueTimeZone value
   */
  public TimeZone valueTimeZone() {
    String temp=prefs.getString("value_time_zone", null);
    return (StringUtils.hasText(temp)) ? TimeZoneUtils.read(temp): null;
  }

  /**
   * read property valueTimeList.
   *
   * @return property valueTimeList value
   */
  public List<Time> valueTimeList() {
    String temp=prefs.getString("value_time_list", null);
    return StringUtils.hasText(temp) ? parseValueTimeList(temp): null;

  }

  /**
   * read property valueStrinList.
   *
   * @return property valueStrinList value
   */
  public LinkedList<String> valueStrinList() {
    String temp=prefs.getString("value_strin_list", null);
    return StringUtils.hasText(temp) ? parseValueStrinList(temp): null;

  }

  /**
   * read property valueLongList.
   *
   * @return property valueLongList value
   */
  public LinkedList<Long> valueLongList() {
    String temp=prefs.getString("value_long_list", null);
    return StringUtils.hasText(temp) ? parseValueLongList(temp): null;

  }

  /**
   * read property valueByteArray.
   *
   * @return property valueByteArray value
   */
  public byte[] valueByteArray() {
    String temp=prefs.getString("value_byte_array", null);
    return StringUtils.hasText(temp) ? parseValueByteArray(temp): null;

  }

  /**
   * read property valueBean.
   *
   * @return property valueBean value
   */
  public Bean valueBean() {
    String temp=prefs.getString("value_bean", null);
    return StringUtils.hasText(temp) ? parseValueBean(temp): null;

  }

  /**
   * read property valueLongTypeArray.
   *
   * @return property valueLongTypeArray value
   */
  public long[] valueLongTypeArray() {
    String temp=prefs.getString("value_long_type_array", null);
    return StringUtils.hasText(temp) ? parseValueLongTypeArray(temp): null;

  }

  /**
   * read property valueLongArray.
   *
   * @return property valueLongArray value
   */
  public Long[] valueLongArray() {
    String temp=prefs.getString("value_long_array", null);
    return StringUtils.hasText(temp) ? parseValueLongArray(temp): null;

  }

  /**
   * read property valueBeanArray.
   *
   * @return property valueBeanArray value
   */
  public Bean[] valueBeanArray() {
    String temp=prefs.getString("value_bean_array", null);
    return StringUtils.hasText(temp) ? parseValueBeanArray(temp): null;

  }

  /**
   * read property valueStringArray.
   *
   * @return property valueStringArray value
   */
  public String[] valueStringArray() {
    String temp=prefs.getString("value_string_array", null);
    return StringUtils.hasText(temp) ? parseValueStringArray(temp): null;

  }

  /**
   * read property valueCharList.
   *
   * @return property valueCharList value
   */
  public LinkedList<Character> valueCharList() {
    String temp=prefs.getString("value_char_list", null);
    return StringUtils.hasText(temp) ? parseValueCharList(temp): null;

  }

  /**
   * read property valueCharTypeArray.
   *
   * @return property valueCharTypeArray value
   */
  public char[] valueCharTypeArray() {
    String temp=prefs.getString("value_char_type_array", null);
    return StringUtils.hasText(temp) ? parseValueCharTypeArray(temp): null;

  }

  /**
   * read property valueCharArray.
   *
   * @return property valueCharArray value
   */
  public Character[] valueCharArray() {
    String temp=prefs.getString("value_char_array", null);
    return StringUtils.hasText(temp) ? parseValueCharArray(temp): null;

  }

  /**
   * read property valueMapStringBean.
   *
   * @return property valueMapStringBean value
   */
  public Map<String, Bean> valueMapStringBean() {
    String temp=prefs.getString("value_map_string_bean", null);
    return StringUtils.hasText(temp) ? parseValueMapStringBean(temp): null;

  }

  /**
   * read property valueLinkedMapStringBean.
   *
   * @return property valueLinkedMapStringBean value
   */
  public LinkedHashMap<String, Bean> valueLinkedMapStringBean() {
    String temp=prefs.getString("value_linked_map_string_bean", null);
    return StringUtils.hasText(temp) ? parseValueLinkedMapStringBean(temp): null;

  }

  /**
   * read property valueSetString.
   *
   * @return property valueSetString value
   */
  public Set<String> valueSetString() {
    Set<String> temp=prefs.getStringSet("value_set_string", defaultBean.valueSetString);
    return temp;

  }

  /**
   * for attribute valueTimeList serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueTimeList(List<Time> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Time item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueTimeList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(SQLTimeUtils.write(item));
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueTimeList parsing.
   *
   * @param input the input
   * @return the list
   */
  protected List<Time> parseValueTimeList(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      List<Time> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Time> collection=new ArrayList<>();
        Time item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=SQLTimeUtils.read(jacksonParser.getText());
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStrinList serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueStrinList(LinkedList<String> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueStrinList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStrinList parsing.
   *
   * @param input the input
   * @return the linked list
   */
  protected LinkedList<String> parseValueStrinList(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedList<String> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<String> collection=new LinkedList<>();
        String item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getText();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongList serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueLongList(LinkedList<Long> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongList parsing.
   *
   * @param input the input
   * @return the linked list
   */
  protected LinkedList<Long> parseValueLongList(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedList<Long> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<Long> collection=new LinkedList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueByteArray serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueByteArray(byte[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        jacksonSerializer.writeBinaryField("valueByteArray", value);
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueByteArray parsing.
   *
   * @param input the input
   * @return the byte[]
   */
  protected byte[] parseValueByteArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      byte[] result=null;
      if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
        result=jacksonParser.getBinaryValue();
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBean serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueBean(Bean value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        beanBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBean parsing.
   *
   * @param input the input
   * @return the bean
   */
  protected Bean parseValueBean(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      Bean result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=beanBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongTypeArray serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueLongTypeArray(long[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongTypeArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongTypeArray parsing.
   *
   * @param input the input
   * @return the long[]
   */
  protected long[] parseValueLongTypeArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      long[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Long> collection=new ArrayList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asLongTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongArray serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueLongArray(Long[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        Long item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueLongArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLongArray parsing.
   *
   * @param input the input
   * @return the long[]
   */
  protected Long[] parseValueLongArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Long[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Long> collection=new ArrayList<>();
        Long item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getLongValue();
          }
          collection.add(item);
        }
        result=CollectionUtils.asLongArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBeanArray serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueBeanArray(Bean[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        Bean item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueBeanArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            beanBindMap.serializeOnJackson(item, jacksonSerializer);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueBeanArray parsing.
   *
   * @param input the input
   * @return the bean[]
   */
  protected Bean[] parseValueBeanArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Bean[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Bean> collection=new ArrayList<>();
        Bean item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=beanBindMap.parseOnJackson(jacksonParser);
          }
          collection.add(item);
        }
        result=CollectionUtils.asArray(collection, new Bean[collection.size()]);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStringArray serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueStringArray(String[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        String item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueStringArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeString(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueStringArray parsing.
   *
   * @param input the input
   * @return the string[]
   */
  protected String[] parseValueStringArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      String[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<String> collection=new ArrayList<>();
        String item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=jacksonParser.getText();
          }
          collection.add(item);
        }
        result=CollectionUtils.asArray(collection, new String[collection.size()]);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharList serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueCharList(LinkedList<Character> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.size();
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharList");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value.get(i);
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharList parsing.
   *
   * @param input the input
   * @return the linked list
   */
  protected LinkedList<Character> parseValueCharList(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedList<Character> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedList<Character> collection=new LinkedList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharTypeArray serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueCharTypeArray(char[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        char item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharTypeArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          jacksonSerializer.writeNumber(item);
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharTypeArray parsing.
   *
   * @param input the input
   * @return the char[]
   */
  protected char[] parseValueCharTypeArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      char[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterTypeArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharArray serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueCharArray(Character[] value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        int n=value.length;
        Character item;
        // write wrapper tag
        jacksonSerializer.writeFieldName("valueCharArray");
        jacksonSerializer.writeStartArray();
        for (int i=0; i<n; i++) {
          item=value[i];
          if (item==null) {
            jacksonSerializer.writeNull();
          } else {
            jacksonSerializer.writeNumber(item);
          }
        }
        jacksonSerializer.writeEndArray();
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueCharArray parsing.
   *
   * @param input the input
   * @return the character[]
   */
  protected Character[] parseValueCharArray(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Character[] result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        ArrayList<Character> collection=new ArrayList<>();
        Character item=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          if (jacksonParser.currentToken()==JsonToken.VALUE_NULL) {
            item=null;
          } else {
            item=Character.valueOf((char)jacksonParser.getIntValue());
          }
          collection.add(item);
        }
        result=CollectionUtils.asCharacterArray(collection);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueMapStringBean serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueMapStringBean(Map<String, Bean> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        // write wrapper tag
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("valueMapStringBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeFieldName("value");
              beanBindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueMapStringBean");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueMapStringBean parsing.
   *
   * @param input the input
   * @return the map
   */
  protected Map<String, Bean> parseValueMapStringBean(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      Map<String, Bean> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        HashMap<String, Bean> collection=new HashMap<>();
        String key=null;
        Bean value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getText();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
            value=beanBindMap.parseOnJackson(jacksonParser);
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLinkedMapStringBean serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeValueLinkedMapStringBean(LinkedHashMap<String, Bean> value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      jacksonSerializer.writeStartObject();
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        // write wrapper tag
        if (value.size()>0) {
          jacksonSerializer.writeFieldName("valueLinkedMapStringBean");
          jacksonSerializer.writeStartArray();
          for (Map.Entry<String, Bean> item: value.entrySet()) {
            jacksonSerializer.writeStartObject();
            jacksonSerializer.writeStringField("key", item.getKey());
            if (item.getValue()==null) {
              jacksonSerializer.writeNullField("value");
            } else {
              jacksonSerializer.writeFieldName("value");
              beanBindMap.serializeOnJackson(item.getValue(), jacksonSerializer);
            }
            jacksonSerializer.writeEndObject();
          }
          jacksonSerializer.writeEndArray();
        } else {
          jacksonSerializer.writeNullField("valueLinkedMapStringBean");
        }
      }
      jacksonSerializer.writeEndObject();
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute valueLinkedMapStringBean parsing.
   *
   * @param input the input
   * @return the linked hash map
   */
  protected LinkedHashMap<String, Bean> parseValueLinkedMapStringBean(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      // value of "element"
      jacksonParser.nextValue();
      LinkedHashMap<String, Bean> result=null;
      if (jacksonParser.currentToken()==JsonToken.START_ARRAY) {
        LinkedHashMap<String, Bean> collection=new LinkedHashMap<>();
        String key=null;
        Bean value=null;
        while (jacksonParser.nextToken() != JsonToken.END_ARRAY) {
          jacksonParser.nextValue();
          key=jacksonParser.getText();
          jacksonParser.nextValue();
          if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
            value=beanBindMap.parseOnJackson(jacksonParser);
          }
          collection.put(key, value);
          key=null;
          value=null;
          jacksonParser.nextToken();
        }
        result=collection;
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences.
   *
   * @return the bind bean shared preferences
   */
  public static synchronized BindBeanSharedPreferences instance() {
    if (instance==null) {
      instance=new BindBeanSharedPreferences();
    }
    return instance;
  }

  /**
   * editor class for shared preferences.
   */
  public class BindEditor extends AbstractEditor {
    
    /**
     * Instantiates a new bind editor.
     */
    private BindEditor() {
    }

    /**
     * modifier for property valueBoolType.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueBoolType(boolean value) {
      editor.putBoolean("value_bool_type",(boolean)value);

      return this;
    }

    /**
     * modifier for property valueBool.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueBool(Boolean value) {
      if (value!=null)  {
        editor.putBoolean("value_bool",(boolean)value);
      }

      return this;
    }

    /**
     * modifier for property valueByteType.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueByteType(byte value) {
      editor.putInt("value_byte_type",(int)value);

      return this;
    }

    /**
     * modifier for property valueByte.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueByte(Byte value) {
      if (value!=null)  {
        editor.putInt("value_byte",(int)value);
      }

      return this;
    }

    /**
     * modifier for property valueShortType.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueShortType(short value) {
      editor.putInt("value_short_type",(int)value);

      return this;
    }

    /**
     * modifier for property valueShort.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueShort(Short value) {
      if (value!=null)  {
        editor.putInt("value_short",(int)value);
      }

      return this;
    }

    /**
     * modifier for property valueIntType.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueIntType(int value) {
      editor.putInt("value_int_type",(int)value);

      return this;
    }

    /**
     * modifier for property valueInt.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueInt(Integer value) {
      if (value!=null)  {
        editor.putInt("value_int",(int)value);
      }

      return this;
    }

    /**
     * modifier for property valueString.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueString(String value) {
      editor.putString("value_string",value);

      return this;
    }

    /**
     * modifier for property valueCharType.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueCharType(char value) {
      editor.putInt("value_char_type",(char)value);

      return this;
    }

    /**
     * modifier for property valueChar.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueChar(Character value) {
      if (value!=null)  {
        editor.putInt("value_char",(char)value);
      }

      return this;
    }

    /**
     * modifier for property valueFloatType.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueFloatType(float value) {
      editor.putFloat("value_float_type",value);

      return this;
    }

    /**
     * modifier for property valueFloat.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueFloat(Float value) {
      if (value!=null)  {
        editor.putFloat("value_float",value);
      }

      return this;
    }

    /**
     * modifier for property valueBigInteger.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueBigInteger(BigInteger value) {
      if (value!=null) editor.putString("value_big_integer",value.toString()); else editor.remove("value_big_integer");
      return this;
    }

    /**
     * modifier for property valueBigDecimal.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueBigDecimal(BigDecimal value) {
      if (value!=null) editor.putString("value_big_decimal",value.toPlainString()); else editor.remove("value_big_decimal");
      return this;
    }

    /**
     * modifier for property valueEnumType.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueEnumType(EnumType value) {
      if (value!=null)  {
        editor.putString("value_enum_type",value.toString() );
      } else {
        editor.remove("valueEnumType");
      }

      return this;
    }

    /**
     * modifier for property valueLongType.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueLongType(long value) {
      editor.putLong("value_long_type",value);

      return this;
    }

    /**
     * modifier for property valueLong.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null)  {
        editor.putLong("value_long",value);
      }

      return this;
    }

    /**
     * modifier for property valueDoubleType.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueDoubleType(double value) {
      editor.putString("value_double_type",String.valueOf(value));

      return this;
    }

    /**
     * modifier for property valueDouble.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueDouble(Double value) {
      if (value!=null)  {
        editor.putString("value_double",String.valueOf(value));
      } else {
        editor.remove("valueDouble");
      }

      return this;
    }

    /**
     * modifier for property valueLocale.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueLocale(Locale value) {
      if (value!=null)  {
        editor.putString("value_locale",LocaleUtils.write(value));
      } else {
        editor.remove("valueLocale");
      }

      return this;
    }

    /**
     * modifier for property valueCalendar.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueCalendar(Calendar value) {
      if (value!=null)  {
        editor.putString("value_calendar",CalendarUtils.write(value));
      } else {
        editor.remove("valueCalendar");
      }

      return this;
    }

    /**
     * modifier for property valueDate.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueDate(Date value) {
      if (value!=null)  {
        editor.putString("value_date",DateUtils.write(value));
      } else {
        editor.remove("valueDate");
      }

      return this;
    }

    /**
     * modifier for property valueUrl.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueUrl(URL value) {
      if (value!=null)  {
        editor.putString("value_url",UrlUtils.write(value));
      } else {
        editor.remove("valueUrl");
      }

      return this;
    }

    /**
     * modifier for property valueTime.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueTime(Time value) {
      if (value!=null)  {
        editor.putString("value_time",SQLTimeUtils.write(value));
      } else {
        editor.remove("valueTime");
      }

      return this;
    }

    /**
     * modifier for property valueCurrency.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueCurrency(Currency value) {
      if (value!=null)  {
        editor.putString("value_currency",CurrencyUtils.write(value));
      } else {
        editor.remove("valueCurrency");
      }

      return this;
    }

    /**
     * modifier for property valueTimeZone.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueTimeZone(TimeZone value) {
      if (value!=null)  {
        editor.putString("value_time_zone",TimeZoneUtils.write(value));
      } else {
        editor.remove("valueTimeZone");
      }

      return this;
    }

    /**
     * modifier for property valueTimeList.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueTimeList(List<Time> value) {
      if (value!=null)  {
        String temp=serializeValueTimeList(value);
        editor.putString("value_time_list",temp);
      }  else  {
        editor.remove("value_time_list");
      }

      return this;
    }

    /**
     * modifier for property valueStrinList.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueStrinList(LinkedList<String> value) {
      if (value!=null)  {
        String temp=serializeValueStrinList(value);
        editor.putString("value_strin_list",temp);
      }  else  {
        editor.remove("value_strin_list");
      }

      return this;
    }

    /**
     * modifier for property valueLongList.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueLongList(LinkedList<Long> value) {
      if (value!=null)  {
        String temp=serializeValueLongList(value);
        editor.putString("value_long_list",temp);
      }  else  {
        editor.remove("value_long_list");
      }

      return this;
    }

    /**
     * modifier for property valueByteArray.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueByteArray(byte[] value) {
      if (value!=null)  {
        String temp=serializeValueByteArray(value);
        editor.putString("value_byte_array",temp);
      }  else  {
        editor.remove("value_byte_array");
      }

      return this;
    }

    /**
     * modifier for property valueBean.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueBean(Bean value) {
      if (value!=null)  {
        String temp=serializeValueBean(value);
        editor.putString("value_bean",temp);
      }  else  {
        editor.remove("value_bean");
      }

      return this;
    }

    /**
     * modifier for property valueLongTypeArray.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueLongTypeArray(long[] value) {
      if (value!=null)  {
        String temp=serializeValueLongTypeArray(value);
        editor.putString("value_long_type_array",temp);
      }  else  {
        editor.remove("value_long_type_array");
      }

      return this;
    }

    /**
     * modifier for property valueLongArray.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueLongArray(Long[] value) {
      if (value!=null)  {
        String temp=serializeValueLongArray(value);
        editor.putString("value_long_array",temp);
      }  else  {
        editor.remove("value_long_array");
      }

      return this;
    }

    /**
     * modifier for property valueBeanArray.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueBeanArray(Bean[] value) {
      if (value!=null)  {
        String temp=serializeValueBeanArray(value);
        editor.putString("value_bean_array",temp);
      }  else  {
        editor.remove("value_bean_array");
      }

      return this;
    }

    /**
     * modifier for property valueStringArray.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueStringArray(String[] value) {
      if (value!=null)  {
        String temp=serializeValueStringArray(value);
        editor.putString("value_string_array",temp);
      }  else  {
        editor.remove("value_string_array");
      }

      return this;
    }

    /**
     * modifier for property valueCharList.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueCharList(LinkedList<Character> value) {
      if (value!=null)  {
        String temp=serializeValueCharList(value);
        editor.putString("value_char_list",temp);
      }  else  {
        editor.remove("value_char_list");
      }

      return this;
    }

    /**
     * modifier for property valueCharTypeArray.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueCharTypeArray(char[] value) {
      if (value!=null)  {
        String temp=serializeValueCharTypeArray(value);
        editor.putString("value_char_type_array",temp);
      }  else  {
        editor.remove("value_char_type_array");
      }

      return this;
    }

    /**
     * modifier for property valueCharArray.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueCharArray(Character[] value) {
      if (value!=null)  {
        String temp=serializeValueCharArray(value);
        editor.putString("value_char_array",temp);
      }  else  {
        editor.remove("value_char_array");
      }

      return this;
    }

    /**
     * modifier for property valueMapStringBean.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueMapStringBean(Map<String, Bean> value) {
      if (value!=null)  {
        String temp=serializeValueMapStringBean(value);
        editor.putString("value_map_string_bean",temp);
      }  else  {
        editor.remove("value_map_string_bean");
      }

      return this;
    }

    /**
     * modifier for property valueLinkedMapStringBean.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueLinkedMapStringBean(LinkedHashMap<String, Bean> value) {
      if (value!=null)  {
        String temp=serializeValueLinkedMapStringBean(value);
        editor.putString("value_linked_map_string_bean",temp);
      }  else  {
        editor.remove("value_linked_map_string_bean");
      }

      return this;
    }

    /**
     * modifier for property valueSetString.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueSetString(Set<String> value) {
      editor.putStringSet("value_set_string",value);

      return this;
    }
  }
}