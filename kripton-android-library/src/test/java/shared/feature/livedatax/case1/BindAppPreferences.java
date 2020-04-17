package shared.feature.livedatax.case1;

import android.content.SharedPreferences;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.sharedprefs.AbstractSharedPreference;
import com.abubusoft.kripton.androidx.livedata.KriptonXLiveDataHandlerImpl;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.KriptonByteArrayOutputStream;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.persistence.JacksonWrapperParser;
import com.abubusoft.kripton.persistence.JacksonWrapperSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class is the shared preference binder defined for AppPreferences
 *
 * @see AppPreferences
 */
public class BindAppPreferences extends AbstractSharedPreference {
  /**
   * instance of shared preferences
   */
  private static BindAppPreferences instance;

  /**
   * working instance of bean
   */
  private final AppPreferences defaultBean;

  @SuppressWarnings("rawtypes")
  private List<Pair<String, WeakReference<LiveDataHandler>>> liveDatas = new CopyOnWriteArrayList<Pair<String, WeakReference<LiveDataHandler>>>();

  /**
   * Listener used to propagate shared prefs changes through RX
   */
  private SharedPreferences.OnSharedPreferenceChangeListener liveDataListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
    @Override
    public void onSharedPreferenceChanged(final SharedPreferences sharedPreferences,
        final String key) {
      KriptonLibrary.getExecutorService().execute(new Runnable() {
        @Override
        public void run() {
          switch (key) {
            // value_boolean - valueBoolean
            case "value_boolean": {
            boolean _value=(boolean)sharedPreferences.getBoolean("value_boolean", (boolean)defaultBean.valueBoolean);
            updateLiveData("value_boolean", _value);
            return;
            }
            // value_long - valueLong
            case "value_long": {
            Long _value=sharedPreferences.getLong("value_long", (defaultBean.valueLong==null?0L:defaultBean.valueLong));
            updateLiveData("value_long", _value);
            return;
            }
            // string_list - stringList
            case "string_list": {
            String temp=sharedPreferences.getString("string_list", null);
            List<String> _value=StringUtils.hasText(temp) ? parseStringList(temp): defaultBean.stringList;

            updateLiveData("string_list", _value);
            return;
            }
            // name - name
            case "name": {
            String _value=sharedPreferences.getString("name", defaultBean.name);
            updateLiveData("name", _value);
            return;
            }
            // description - description
            case "description": {
            String _value=sharedPreferences.getString("description", defaultBean.getDescription());
            updateLiveData("description", _value);
            return;
            }
            // string_array - stringArray
            case "string_array": {
            String temp=sharedPreferences.getString("string_array", null);
            String[] _value=StringUtils.hasText(temp) ? parseStringArray(temp): defaultBean.getStringArray();

            updateLiveData("string_array", _value);
            return;
            }
            // value_int - valueInt
            case "value_int": {
            int _value=(int)sharedPreferences.getInt("value_int", (int)defaultBean.valueInt);
            updateLiveData("value_int", _value);
            return;
            }
            // value_float - valueFloat
            case "value_float": {
            float _value=sharedPreferences.getFloat("value_float", defaultBean.valueFloat);
            updateLiveData("value_float", _value);
            return;
            }
            default: return;
          }
        }
      });
    }
  };

  /**
   * constructor
   */
  private BindAppPreferences() {
    createPrefs();
    defaultBean=new AppPreferences();
  }

  /**
   * create an editor to modify shared preferences
   */
  public BindEditor edit() {
    return new BindEditor();
  }

  /**
   * create prefs
   */
  private void createPrefs() {
    // no typeName specified, using default shared preferences
    prefs=PreferenceManager.getDefaultSharedPreferences(KriptonLibrary.getContext());
    prefs.registerOnSharedPreferenceChangeListener(liveDataListener);
  }

  /**
   * force to refresh values
   */
  public BindAppPreferences refresh() {
    createPrefs();
    return this;
  }

  @SuppressWarnings("rawtypes")
  protected void registryLiveData(String key, LiveDataHandler value) {
    liveDatas.add(new Pair<String , WeakReference<LiveDataHandler>>(key, new WeakReference<LiveDataHandler>(value)));
  }

  @SuppressWarnings("rawtypes")
  protected void updateLiveData(String key, Object value) {
    for (Pair<String, WeakReference<LiveDataHandler>> item : liveDatas) {
      if (item.value0.equals(key) && item.value1.get() != null) {
        item.value1.get().invalidate();
      }
    }
  }

  /**
   * Obtains an LiveData to <code>valueBoolean</code> property
   *
   * @return
   * an LiveData to <code>valueBoolean</code> property
   */
  public MutableLiveData<Boolean> getValueBooleanAsLiveData() {
    KriptonXLiveDataHandlerImpl<Boolean> liveData=new KriptonXLiveDataHandlerImpl<Boolean>() {
      @Override
      protected Boolean compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getValueBoolean();
      }
    };
    registryLiveData("value_boolean", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>valueLong</code> property
   *
   * @return
   * an LiveData to <code>valueLong</code> property
   */
  public MutableLiveData<Long> getValueLongAsLiveData() {
    KriptonXLiveDataHandlerImpl<Long> liveData=new KriptonXLiveDataHandlerImpl<Long>() {
      @Override
      protected Long compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getValueLong();
      }
    };
    registryLiveData("value_long", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>stringList</code> property
   *
   * @return
   * an LiveData to <code>stringList</code> property
   */
  public MutableLiveData<List<String>> getStringListAsLiveData() {
    KriptonXLiveDataHandlerImpl<List<String>> liveData=new KriptonXLiveDataHandlerImpl<List<String>>() {
      @Override
      protected List<String> compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getStringList();
      }
    };
    registryLiveData("string_list", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>name</code> property
   *
   * @return
   * an LiveData to <code>name</code> property
   */
  public MutableLiveData<String> getNameAsLiveData() {
    KriptonXLiveDataHandlerImpl<String> liveData=new KriptonXLiveDataHandlerImpl<String>() {
      @Override
      protected String compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getName();
      }
    };
    registryLiveData("name", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>description</code> property
   *
   * @return
   * an LiveData to <code>description</code> property
   */
  public MutableLiveData<String> getDescriptionAsLiveData() {
    KriptonXLiveDataHandlerImpl<String> liveData=new KriptonXLiveDataHandlerImpl<String>() {
      @Override
      protected String compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getDescription();
      }
    };
    registryLiveData("description", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>stringArray</code> property
   *
   * @return
   * an LiveData to <code>stringArray</code> property
   */
  public MutableLiveData<String[]> getStringArrayAsLiveData() {
    KriptonXLiveDataHandlerImpl<String[]> liveData=new KriptonXLiveDataHandlerImpl<String[]>() {
      @Override
      protected String[] compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getStringArray();
      }
    };
    registryLiveData("string_array", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>valueInt</code> property
   *
   * @return
   * an LiveData to <code>valueInt</code> property
   */
  public MutableLiveData<Integer> getValueIntAsLiveData() {
    KriptonXLiveDataHandlerImpl<Integer> liveData=new KriptonXLiveDataHandlerImpl<Integer>() {
      @Override
      protected Integer compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getValueInt();
      }
    };
    registryLiveData("value_int", liveData);
    return liveData.getLiveData();
  }

  /**
   * Obtains an LiveData to <code>valueFloat</code> property
   *
   * @return
   * an LiveData to <code>valueFloat</code> property
   */
  public MutableLiveData<Float> getValueFloatAsLiveData() {
    KriptonXLiveDataHandlerImpl<Float> liveData=new KriptonXLiveDataHandlerImpl<Float>() {
      @Override
      protected Float compute() {
        BindAppPreferences.this.refresh();
        return BindAppPreferences.this.getValueFloat();
      }
    };
    registryLiveData("value_float", liveData);
    return liveData.getLiveData();
  }

  /**
   * reset shared preferences
   */
  public void reset() {
    AppPreferences bean=new AppPreferences();
    write(bean);
  }

  /**
   * read bean entirely
   *
   * @return read bean
   */
  public AppPreferences read() {
    AppPreferences bean=new AppPreferences();
    bean.valueBoolean=(boolean)prefs.getBoolean("value_boolean", (boolean)defaultBean.valueBoolean);
    bean.valueLong=prefs.getLong("value_long", (defaultBean.valueLong==null?0L:defaultBean.valueLong));
     {
      String temp=prefs.getString("string_list", null);
      bean.stringList=StringUtils.hasText(temp) ? parseStringList(temp): defaultBean.stringList;
    }

    bean.name=prefs.getString("name", defaultBean.name);
    bean.setDescription(prefs.getString("description", defaultBean.getDescription()));
     {
      String temp=prefs.getString("string_array", null);
      bean.setStringArray(StringUtils.hasText(temp) ? parseStringArray(temp): defaultBean.getStringArray());
    }

    bean.valueInt=(int)prefs.getInt("value_int", (int)defaultBean.valueInt);
    bean.valueFloat=prefs.getFloat("value_float", defaultBean.valueFloat);

    return bean;
  }

  /**
   * write bean entirely
   *
   * @param bean bean to entirely write
   */
  public void write(AppPreferences bean) {
    SharedPreferences.Editor editor=prefs.edit();
    editor.putBoolean("value_boolean",(boolean)bean.valueBoolean);

    if (bean.valueLong!=null)  {
      editor.putLong("value_long",bean.valueLong);
    }

    if (bean.stringList!=null)  {
      String temp=serializeStringList(bean.stringList);
      editor.putString("string_list",temp);
    }  else  {
      editor.remove("string_list");
    }

    editor.putString("name",bean.name);

    editor.putString("description",bean.getDescription());

    if (bean.getStringArray()!=null)  {
      String temp=serializeStringArray(bean.getStringArray());
      editor.putString("string_array",temp);
    }  else  {
      editor.remove("string_array");
    }

    editor.putInt("value_int",(int)bean.valueInt);

    editor.putFloat("value_float",bean.valueFloat);


    editor.commit();
  }

  /**
   * reads property <code>valueBoolean</code> from shared pref with key <code>value_boolean</code>
   *
   * @return property valueBoolean value
   */
  public boolean getValueBoolean() {
    return (boolean)prefs.getBoolean("value_boolean", (boolean)defaultBean.valueBoolean);}

  /**
   * reads property <code>valueLong</code> from shared pref with key <code>value_long</code>
   *
   * @return property valueLong value
   */
  public Long getValueLong() {
    return prefs.getLong("value_long", (defaultBean.valueLong==null?0L:defaultBean.valueLong));}

  /**
   * reads property <code>stringList</code> from shared pref with key <code>string_list</code>
   *
   * @return property stringList value
   */
  public List<String> getStringList() {
    String temp=prefs.getString("string_list", null);
    return StringUtils.hasText(temp) ? parseStringList(temp): defaultBean.stringList;
  }

  /**
   * reads property <code>name</code> from shared pref with key <code>name</code>
   *
   * @return property name value
   */
  public String getName() {
    return prefs.getString("name", defaultBean.name);}

  /**
   * reads property <code>description</code> from shared pref with key <code>description</code>
   *
   * @return property description value
   */
  public String getDescription() {
    return prefs.getString("description", defaultBean.getDescription());}

  /**
   * reads property <code>stringArray</code> from shared pref with key <code>string_array</code>
   *
   * @return property stringArray value
   */
  public String[] getStringArray() {
    String temp=prefs.getString("string_array", null);
    return StringUtils.hasText(temp) ? parseStringArray(temp): defaultBean.getStringArray();
  }

  /**
   * reads property <code>valueInt</code> from shared pref with key <code>value_int</code>
   *
   * @return property valueInt value
   */
  public int getValueInt() {
    return (int)prefs.getInt("value_int", (int)defaultBean.valueInt);}

  /**
   * reads property <code>valueFloat</code> from shared pref with key <code>value_float</code>
   *
   * @return property valueFloat value
   */
  public float getValueFloat() {
    return prefs.getFloat("value_float", defaultBean.valueFloat);}

  /**
   * for attribute stringList serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute stringList parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute stringArray serialization
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * for attribute stringArray parsing
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
      e.printStackTrace();
      throw(new KriptonRuntimeException(e.getMessage()));
    }
  }

  /**
   * get instance of shared preferences
   */
  public static synchronized BindAppPreferences getInstance() {
    if (instance==null) {
      instance=new BindAppPreferences();
      // read and write instance to sync with default values
      instance.write(instance.read());
    }
    return instance;
  }

  /**
   * editor class for shared preferences
   */
  public class BindEditor extends AbstractEditor {
    private BindEditor() {
    }

    /**
     * modifier for property valueBoolean
     */
    public BindEditor putValueBoolean(boolean value) {
      editor.putBoolean("value_boolean",(boolean)value);

      return this;
    }

    /**
     * remove property valueBoolean
     */
    public BindEditor removeValueBoolean() {
      editor.remove("value_boolean");
      return this;
    }

    /**
     * modifier for property valueLong
     */
    public BindEditor putValueLong(Long value) {
      if (value!=null)  {
        editor.putLong("value_long",value);
      }

      return this;
    }

    /**
     * remove property valueLong
     */
    public BindEditor removeValueLong() {
      editor.remove("value_long");
      return this;
    }

    /**
     * modifier for property stringList
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
     * remove property stringList
     */
    public BindEditor removeStringList() {
      editor.remove("string_list");
      return this;
    }

    /**
     * modifier for property name
     */
    public BindEditor putName(String value) {
      editor.putString("name",value);

      return this;
    }

    /**
     * remove property name
     */
    public BindEditor removeName() {
      editor.remove("name");
      return this;
    }

    /**
     * modifier for property description
     */
    public BindEditor putDescription(String value) {
      editor.putString("description",value);

      return this;
    }

    /**
     * remove property description
     */
    public BindEditor removeDescription() {
      editor.remove("description");
      return this;
    }

    /**
     * modifier for property stringArray
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
     * remove property stringArray
     */
    public BindEditor removeStringArray() {
      editor.remove("string_array");
      return this;
    }

    /**
     * modifier for property valueInt
     */
    public BindEditor putValueInt(int value) {
      editor.putInt("value_int",(int)value);

      return this;
    }

    /**
     * remove property valueInt
     */
    public BindEditor removeValueInt() {
      editor.remove("value_int");
      return this;
    }

    /**
     * modifier for property valueFloat
     */
    public BindEditor putValueFloat(float value) {
      editor.putFloat("value_float",value);

      return this;
    }

    /**
     * remove property valueFloat
     */
    public BindEditor removeValueFloat() {
      editor.remove("value_float");
      return this;
    }

    /**
     * clear all properties
     */
    public BindEditor clear() {
      editor.clear();
      return this;
    }
  }
}
