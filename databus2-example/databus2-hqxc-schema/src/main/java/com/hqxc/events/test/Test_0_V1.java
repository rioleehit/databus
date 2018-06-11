package com.hqxc.events.test;


@SuppressWarnings("all")
/** Auto-generated Avro schema for sy$person. Generated at Dec 04, 2012 05:07:05 PM PST */
public class Test_0_V1 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = org.apache.avro.Schema.parse("{\"name\":\"Test_0_V1\",\"doc\":\"Auto-generated Avro schema for sy$person. Generated at Dec 04, 2012 05:07:05 PM PST\",\"type\":\"record\",\"meta\":\"dbFieldName=t_test_source_0;pk=id;\",\"namespace\":\"com.hqxc.events.test\",\"fields\":[{\"name\":\"id\",\"type\":[\"long\",\"null\"],\"meta\":\"dbFieldName=id;dbFieldPosition=0;\"},{\"name\":\"test_char\",\"type\":[\"string\",\"null\"],\"meta\":\"dbFieldName=test_char;dbFieldPosition=1;\"},{\"name\":\"test_int\",\"type\":[\"long\",\"null\"],\"meta\":\"dbFieldName=test_int;dbFieldPosition=2;\"},{\"name\":\"test_text\",\"type\":[\"string\",\"null\"],\"meta\":\"dbFieldName=test_text;dbFieldPosition=3;\"},{\"name\":\"test_float\",\"type\":[\"float\",\"null\"],\"meta\":\"dbFieldName=test_float;dbFieldPosition=4;\"},{\"name\":\"test_double\",\"type\":[\"double\",\"null\"],\"meta\":\"dbFieldName=test_double;dbFieldPosition=5;\"},{\"name\":\"birth_date\",\"type\":[\"long\",\"null\"],\"meta\":\"dbFieldName=birth_date;dbFieldPosition=6;\"},{\"name\":\"deleted\",\"type\":[\"string\",\"null\"],\"meta\":\"dbFieldName=DELETED;dbFieldPosition=7;\"}]}");
  public java.lang.Long id;
  public java.lang.CharSequence test_char;
  public java.lang.Long test_int;
  public java.lang.CharSequence test_text;
  public java.lang.Float test_float;
  public java.lang.Double test_double;
  public java.lang.Long birthDate;
  public java.lang.CharSequence deleted;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
      case 0: return id;
      case 1: return test_char;
      case 2: return test_int;
      case 3: return test_text;
      case 4:return test_float;
      case 5:return test_double;
      case 6: return birthDate;
      case 7: return deleted;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      case 0: id = (java.lang.Long)value$; break;
      case 1: test_char = (java.lang.CharSequence)value$; break;
      case 2: test_int = (java.lang.Long)value$; break;
      case 3: test_text = (java.lang.CharSequence)value$; break;
      case 4: test_float = (java.lang.Float)value$; break;
      case 5: test_double = (java.lang.Double)value$; break;
      case 6: birthDate = (java.lang.Long)value$; break;
      case 7: deleted = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
}
