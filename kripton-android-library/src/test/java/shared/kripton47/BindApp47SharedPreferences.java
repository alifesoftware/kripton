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
package shared.kripton47;

import android.content.Context;
import android.content.SharedPreferences;
import com.abubusoft.kripton.BinderUtils;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

// TODO: Auto-generated Javadoc
/**
 * This class is the shared preference binder defined for App47.
 *
 * @see App47
 */
public class BindApp47SharedPreferences extends AbstractSharedPreference {
  
  /** shared preferences typeName for App47. */
  public static final String SHARED_PREFERENCE_NAME = "app47";

  /** instance of shared preferences. */
  private static BindApp47SharedPreferences instance;

  /** working instance of bean. */
  private final App47 defaultBean;

  /** UserAccessTokenBindMap. */
  private UserAccessTokenBindMap userAccessTokenBindMap = BinderUtils.mapperFor(UserAccessToken.class);

  /**
   * constructor.
   */
  private BindApp47SharedPreferences() {
    // using typeName attribute of annotation @BindSharedPreferences as typeName
    prefs=KriptonLibrary.context().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    defaultBean=new App47();
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
   * @return the bind app 47 shared preferences
   */
  public BindApp47SharedPreferences refresh() {
    // using typeName attribute of annotation @BindSharedPreferences as typeName
    prefs=KriptonLibrary.context().getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    return this;
  }

  /**
   * reset shared preferences.
   */
  public void reset() {
    App47 bean=new App47();
    write(bean);
  }

  /**
   * read bean entirely.
   *
   * @return read bean
   */
  public App47 read() {
    App47 bean=new App47();
    bean.name=prefs.getString("name", bean.name);
     {
      String temp=prefs.getString("user_access_token", null);
      bean.userAccessToken=StringUtils.hasText(temp) ? parseUserAccessToken(temp): null;
    }


    return bean;
  }

  /**
   * write bean entirely.
   *
   * @param bean bean to entirely write
   */
  public void write(App47 bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putString("name",bean.name);

    if (bean.userAccessToken!=null)  {
      String temp=serializeUserAccessToken(bean.userAccessToken);
      editor.putString("user_access_token",temp);
    }  else  {
      editor.remove("user_access_token");
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
   * read property userAccessToken.
   *
   * @return property userAccessToken value
   */
  public UserAccessToken userAccessToken() {
    String temp=prefs.getString("user_access_token", null);
    return StringUtils.hasText(temp) ? parseUserAccessToken(temp): null;

  }

  /**
   * for attribute userAccessToken serialization.
   *
   * @param value the value
   * @return the string
   */
  protected String serializeUserAccessToken(UserAccessToken value) {
    if (value==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (KriptonByteArrayOutputStream stream=new KriptonByteArrayOutputStream(); JacksonWrapperSerializer wrapper=context.createSerializer(stream)) {
      JsonGenerator jacksonSerializer=wrapper.jacksonGenerator;
      int fieldCount=0;
      if (value!=null)  {
        fieldCount++;
        userAccessTokenBindMap.serializeOnJackson(value, jacksonSerializer);
      }
      jacksonSerializer.flush();
      return stream.toString();
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute userAccessToken parsing.
   *
   * @param input the input
   * @return the user access token
   */
  protected UserAccessToken parseUserAccessToken(String input) {
    if (input==null) {
      return null;
    }
    KriptonJsonContext context=KriptonBinder.jsonBind();
    try (JacksonWrapperParser wrapper=context.createParser(input)) {
      JsonParser jacksonParser=wrapper.jacksonParser;
      // START_OBJECT
      jacksonParser.nextToken();
      UserAccessToken result=null;
      if (jacksonParser.currentToken()==JsonToken.START_OBJECT) {
        result=userAccessTokenBindMap.parseOnJackson(jacksonParser);
      }
      return result;
    } catch(Exception e) {
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences.
   *
   * @return the bind app 47 shared preferences
   */
  public static synchronized BindApp47SharedPreferences instance() {
    if (instance==null) {
      instance=new BindApp47SharedPreferences();
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
     * modifier for property userAccessToken.
     *
     * @param value the value
     * @return the bind editor
     */
    public BindEditor putUserAccessToken(UserAccessToken value) {
      if (value!=null)  {
        String temp=serializeUserAccessToken(value);
        editor.putString("user_access_token",temp);
      }  else  {
        editor.remove("user_access_token");
      }

      return this;
    }
  }
}