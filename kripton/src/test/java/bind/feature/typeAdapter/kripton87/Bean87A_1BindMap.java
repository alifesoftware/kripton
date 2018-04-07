package bind.feature.typeAdapter.kripton87;

import com.abubusoft.kripton.AbstractMapper;
import com.abubusoft.kripton.annotation.BindMap;
import com.abubusoft.kripton.common.PrimitiveUtils;
import com.abubusoft.kripton.common.TypeAdapterUtils;
import com.abubusoft.kripton.escape.StringEscapeUtils;
import com.abubusoft.kripton.xml.XMLParser;
import com.abubusoft.kripton.xml.XMLSerializer;
import com.abubusoft.kripton.xml.XmlPullParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

// TODO: Auto-generated Javadoc
/**
 * This class is binder map for Bean87A_1.
 *
 * @see Bean87A_1
 */
@BindMap(Bean87A_1.class)
public class Bean87A_1BindMap extends AbstractMapper<Bean87A_1> {
  
  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJackson(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJackson(Bean87A_1 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueDate (mapped with "valueDate")
    if (object.valueDate!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeAdapter.kripton87.DateLongTypeAdapter
      jacksonSerializer.writeNumberField("valueDate", TypeAdapterUtils.toData(DateLongTypeAdapter.class, object.valueDate));
    }

    // field valueDescription (mapped with "valueDescription")
    if (object.valueDescription!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeAdapter.kripton87.StringInverterTypeAdapter
      jacksonSerializer.writeStringField("valueDescription", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.valueDescription));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /* (non-Javadoc)
   * @see com.abubusoft.kripton.BinderMapper#serializeOnJacksonAsString(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator)
   */
  @Override
  public int serializeOnJacksonAsString(Bean87A_1 object, JsonGenerator jacksonSerializer) throws
      Exception {
    jacksonSerializer.writeStartObject();
    int fieldCount=0;

    // Serialized Field:

    // field valueDate (mapped with "valueDate")
    if (object.valueDate!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.DateLongTypeAdapter
      jacksonSerializer.writeStringField("valueDate", PrimitiveUtils.writeLong(TypeAdapterUtils.toData(DateLongTypeAdapter.class, object.valueDate)));
    }

    // field valueDescription (mapped with "valueDescription")
    if (object.valueDescription!=null)  {
      fieldCount++;
      // using type adapter bind.feature.typeAdapter.kripton87.StringInverterTypeAdapter
      jacksonSerializer.writeStringField("valueDescription", TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.valueDescription));
    }

    jacksonSerializer.writeEndObject();
    return fieldCount;
  }

  /**
   * method for xml serialization.
   *
   * @param object the object
   * @param xmlSerializer the xml serializer
   * @param currentEventType the current event type
   * @throws Exception the exception
   */
  @Override
  public void serializeOnXml(Bean87A_1 object, XMLSerializer xmlSerializer, int currentEventType)
      throws Exception {
    if (currentEventType == 0) {
      xmlSerializer.writeStartElement("bean87A_1");
    }

    // Persisted fields:

    // field valueDate (mapped with "valueDate")
    // field trasformation java.lang.Long bind.feature.typeAdapter.kripton87.DateLongTypeAdapter 
    if (object.valueDate!=null)  {
      // using type adapter bind.feature.typeAdapter.kripton87.DateLongTypeAdapter
      xmlSerializer.writeStartElement("valueDate");
      xmlSerializer.writeLong(TypeAdapterUtils.toData(DateLongTypeAdapter.class, object.valueDate));
      xmlSerializer.writeEndElement();
    }

    // field valueDescription (mapped with "valueDescription")
    // field trasformation java.lang.String bind.feature.typeAdapter.kripton87.StringInverterTypeAdapter 
    if (object.valueDescription!=null) {
      // using type adapter bind.feature.typeAdapter.kripton87.StringInverterTypeAdapter
      xmlSerializer.writeStartElement("valueDescription");
      xmlSerializer.writeCharacters(StringEscapeUtils.escapeXml10(TypeAdapterUtils.toData(StringInverterTypeAdapter.class, object.valueDescription)));
      xmlSerializer.writeEndElement();
    }

    if (currentEventType == 0) {
      xmlSerializer.writeEndElement();
    }
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the bean 87 A 1
   * @throws Exception the exception
   */
  @Override
  public Bean87A_1 parseOnJackson(JsonParser jacksonParser) throws Exception {
    Bean87A_1 instance = new Bean87A_1();
    String fieldName;
    if (jacksonParser.currentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.currentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "valueDate":
            // field valueDate (mapped with "valueDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.DateLongTypeAdapter
              instance.valueDate=TypeAdapterUtils.toJava(DateLongTypeAdapter.class, jacksonParser.getLongValue());
            }
          break;
          case "valueDescription":
            // field valueDescription (mapped with "valueDescription")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.StringInverterTypeAdapter
              instance.valueDescription=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse with jackson.
   *
   * @param jacksonParser the jackson parser
   * @return the bean 87 A 1
   * @throws Exception the exception
   */
  @Override
  public Bean87A_1 parseOnJacksonAsString(JsonParser jacksonParser) throws Exception {
    Bean87A_1 instance = new Bean87A_1();
    String fieldName;
    if (jacksonParser.getCurrentToken() == null) {
      jacksonParser.nextToken();
    }
    if (jacksonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jacksonParser.skipChildren();
      return instance;
    }
    while (jacksonParser.nextToken() != JsonToken.END_OBJECT) {
      fieldName = jacksonParser.getCurrentName();
      jacksonParser.nextToken();

      // Parse fields:
      switch (fieldName) {
          case "valueDate":
            // field valueDate (mapped with "valueDate")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.DateLongTypeAdapter
              instance.valueDate=TypeAdapterUtils.toJava(DateLongTypeAdapter.class, PrimitiveUtils.readLong(jacksonParser.getText(), null));
            }
          break;
          case "valueDescription":
            // field valueDescription (mapped with "valueDescription")
            if (jacksonParser.currentToken()!=JsonToken.VALUE_NULL) {
              // using type adapter bind.feature.typeAdapter.kripton87.StringInverterTypeAdapter
              instance.valueDescription=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, jacksonParser.getText());
            }
          break;
          default:
            jacksonParser.skipChildren();
          break;}
    }
    return instance;
  }

  /**
   * parse xml.
   *
   * @param xmlParser the xml parser
   * @param currentEventType the current event type
   * @return the bean 87 A 1
   * @throws Exception the exception
   */
  @Override
  public Bean87A_1 parseOnXml(XMLParser xmlParser, int currentEventType) throws Exception {
    Bean87A_1 instance = new Bean87A_1();
    int eventType = currentEventType;
    boolean read=true;

    if (currentEventType == 0) {
      eventType = xmlParser.next();
    } else {
      eventType = xmlParser.getEventType();
    }
    String currentTag = xmlParser.getName().toString();
    String elementName = currentTag;
    // No attributes found

    //sub-elements
    while (xmlParser.hasNext() && elementName!=null) {
      if (read) {
        eventType = xmlParser.next();
      } else {
        eventType = xmlParser.getEventType();
      }
      read=true;
      switch(eventType) {
          case XmlPullParser.START_TAG:
            currentTag = xmlParser.getName().toString();
            switch(currentTag) {
                case "valueDate":
                  // property valueDate (mapped on "valueDate")
                  // using type adapter bind.feature.typeAdapter.kripton87.DateLongTypeAdapter
                  instance.valueDate=TypeAdapterUtils.toJava(DateLongTypeAdapter.class, PrimitiveUtils.readLong(xmlParser.getElementAsLong(), null));
                break;
                case "valueDescription":
                  // property valueDescription (mapped on "valueDescription")
                  // using type adapter bind.feature.typeAdapter.kripton87.StringInverterTypeAdapter
                  instance.valueDescription=TypeAdapterUtils.toJava(StringInverterTypeAdapter.class, StringEscapeUtils.unescapeXml(xmlParser.getElementText()));
                break;
                default:
                break;
              }
            break;
            case XmlPullParser.END_TAG:
              if (elementName.equals(xmlParser.getName())) {
                currentTag = elementName;
                elementName = null;
              }
            break;
            case XmlPullParser.CDSECT:
            case XmlPullParser.TEXT:
              // no property is binded to VALUE o CDATA break;
            default:
            break;
        }
      }
      return instance;
    }
  }