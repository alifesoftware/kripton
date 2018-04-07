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
package shared.kripton50;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * This class is the shared preference binder defined for RightPreferences.
 *
 * @see RightPreferences
 */
public class BindRightPreferences extends AbstractSharedPreference {
  
  /** instance of shared preferences. */
  private static BindRightPreferences instance;

  /** working instance of bean. */
  private final RightPreferences defaultBean;

  /**
   * constructor.
   */
  private BindRightPreferences() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    defaultBean=new RightPreferences();
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
   * @return the bind right preferences
   */
  public BindRightPreferences refresh() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.context());
    return this;
  }

  /**
   * reset shared preferences.
   */
  public void reset() {
    RightPreferences bean=new RightPreferences();
    write(bean);
  }

  /**
   * read bean entirely.
   *
   * @return read bean
   */
  public RightPreferences read() {
    RightPreferences bean=new RightPreferences();
    bean.name=prefs.getString("name", bean.name);
    bean.setDescription(prefs.getString("description", bean.getDescription()));
    bean.valueFloat=prefs.getFloat("value_float", bean.valueFloat);
    bean.valueBoolean=(boolean)prefs.getBoolean("value_boolean", (boolean)bean.valueBoolean);
     {
      String temp=prefs.getString("string_array", null);
      bean.setStringArray(StringUtils.hasText(temp) ? parseStringArray(temp): null);
    }

     {
      String temp=prefs.getString("string_list", null);
      bean.stringList=StringUtils.hasText(temp) ? parseStringList(temp): null;
    }

    bean.valueInt=(int)prefs.getInt("value_int", (int)bean.valueInt);
    bean.valueLong=prefs.getLong("value_long", (bean.valueLong==null?0L:bean.valueLong));

    return bean;
  }

  /**
   * write bean entirely.
   *
   * @param bean bean to entirely write
   */
  public void write(RightPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("name",bean.name);

    editor.putString("description",bean.getDescription());

    editor.putFloat("value_float",bean.valueFloat);

    editor.putBoolean("value_boolean",(boolean)bean.valueBoolean);

    if (bean.getStringArray()!=null)  {
      String temp=serializeStringArray(bean.getStringArray());
      editor.putString("string_array",temp);
    }  else  {
      editor.remove("string_array");
    }

    if (bean.stringList!=null)  {
      String temp=serializeStringList(bean.stringList);
      editor.putString("string_list",temp);
    }  else  {
      editor.remove("string_list");
    }

    editor.putInt("value_int",(int)bean.valueInt);

    if (bean.valueLong!=null)  {
      editor.putLong("value_long",bean.valueLong);
    }


    editor.commit();
  }

  /**
   * read property name.
   *
   * @return property name value
   */
  public String name() {
    return prefs.getString("name", defaultBean.name);
  }

  /**
   * read property description.
   *
   * @return property description value
   */
  public String description() {
    return prefs.getString("description", defaultBean.getDescription());
  }

  /**
   * read property valueFloat.
   *
   * @return property valueFloat value
   */
  public float valueFloat() {
    return prefs.getFloat("value_float", defaultBean.valueFloat);
  }

  /**
   * read property valueBoolean.
   *
   * @return property valueBoolean value
   */
  public boolean valueBoolean() {
    return (boolean)prefs.getBoolean("value_boolean", (boolean)defaultBean.valueBoolean);
  }

  /**
   * read property stringArray.
   *
   * @return property stringArray value
   */
  public String[] stringArray() {
    String temp=prefs.getString("string_array", null);
    return StringUtils.hasText(temp) ? parseStringArray(temp): null;

  }

  /**
   * read property stringList.
   *
   * @return property stringList value
   */
  public List<String> stringList() {
    String temp=prefs.getString("string_list", null);
    return StringUtils.hasText(temp) ? parseStringList(temp): null;

  }

  /**
   * read property valueInt.
   *
   * @return property valueInt value
   */
  public int valueInt() {
    return (int)prefs.getInt("value_int", (int)defaultBean.valueInt);
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
   * for attribute stringArray serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeStringArray(String[] value) {
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
        jacksonSerializer.writeFieldName("stringArray");
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
   * for attribute stringArray parsing.
   *
   * @param input the input
   * @return the string[]
   */
  protected String[] parseStringArray(String input) {
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
   * for attribute stringList serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeStringList(List<String> value) {
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
        jacksonSerializer.writeFieldName("stringList");
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
   * for attribute stringList parsing.
   *
   * @param input the input
   * @return the list
   */
  protected List<String> parseStringList(String input) {
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
      List<String> result=null;
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
   * @return the bind right preferences
   */
  public static synchronized BindRightPreferences instance() {
    if (instance==null) {
      instance=new BindRightPreferences();
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
     * modifier for property name.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putName(String value) {
      editor.putString("name",value);

      return this;
    }

    /**
     * modifier for property description.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putDescription(String value) {
      editor.putString("description",value);

      return this;
    }

    /**
     * modifier for property valueFloat.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueFloat(float value) {
      editor.putFloat("value_float",value);

      return this;
    }

    /**
     * modifier for property valueBoolean.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueBoolean(boolean value) {
      editor.putBoolean("value_boolean",(boolean)value);

      return this;
    }

    /**
     * modifier for property stringArray.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putStringArray(String[] value) {
      if (value!=null)  {
        String temp=serializeStringArray(value);
        editor.putString("string_array",temp);
      }  else  {
        editor.remove("string_array");
      }

      return this;
    }

    /**
     * modifier for property stringList.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putStringList(List<String> value) {
      if (value!=null)  {
        String temp=serializeStringList(value);
        editor.putString("string_list",temp);
      }  else  {
        editor.remove("string_list");
      }

      return this;
    }

    /**
     * modifier for property valueInt.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putValueInt(int value) {
      editor.putInt("value_int",(int)value);

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
  }
}