package com.hqxc.events.test;


@SuppressWarnings("all")
/** Auto-generated Avro schema for sy$person. Generated at Dec 04, 2012 05:07:05 PM PST */
public class Test_1_V1 extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = org.apache.avro.Schema.parse("{\"name\":\"Test_1_V1\",\"doc\":\"Auto-generated Avro schema for sy$person. Generated at Dec 04, 2012 05:07:05 PM PST\",\"type\":\"record\",\"meta\":\"dbFieldName=t_test_source_1;pk=id;\",\"namespace\":\"com.hqxc.events.test\",\"fields\":[{\"name\":\"id\",\"type\":[\"long\",\"null\"],\"meta\":\"dbFieldName=id;dbFieldPosition=0;\"},{\"name\":\"test_char\",\"type\":[\"string\",\"null\"],\"meta\":\"dbFieldName=test_char;dbFieldPosition=1;\"},{\"name\":\"test_int\",\"type\":[\"long\",\"null\"],\"meta\":\"dbFieldName=test_int;dbFieldPosition=2;\"},{\"name\":\"test_text\",\"type\":[\"string\",\"null\"],\"meta\":\"dbFieldName=test_text;dbFieldPosition=3;\"},{\"name\":\"test_float\",\"type\":[\"float\",\"null\"],\"meta\":\"dbFieldName=test_float;dbFieldPosition=4;\"},{\"name\":\"test_double\",\"type\":[\"double\",\"null\"],\"meta\":\"dbFieldName=test_double;dbFieldPosition=5;\"},{\"name\":\"birth_date\",\"type\":[\"long\",\"null\"],\"meta\":\"dbFieldName=birth_date;dbFieldPosition=6;\"},{\"name\":\"deleted\",\"type\":[\"string\",\"null\"],\"meta\":\"dbFieldName=DELETED;dbFieldPosition=7;\"}]}");
  public java.lang.Long id;
  public java.lang.CharSequence test_char2;
  public java.lang.Long test_int2;
  public java.lang.CharSequence test_text2;
  public java.lang.Float test_float2;
  public java.lang.Double test_double2;
  public java.lang.Long birthDate2;
  public java.lang.CharSequence deleted2;
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
      case 0: return id;
      case 1: return test_char2;
      case 2: return test_int2;
      case 3: return test_text2;
      case 4:return test_float2;
      case 5:return test_double2;
      case 6: return birthDate2;
      case 7: return deleted2;
      default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
      case 0: id = (java.lang.Long)value$; break;
      case 1: test_char2 = (java.lang.CharSequence)value$; break;
      case 2: test_int2 = (java.lang.Long)value$; break;
      case 3: test_text2 = (java.lang.CharSequence)value$; break;
      case 4: test_float2 = (java.lang.Float)value$; break;
      case 5: test_double2 = (java.lang.Double)value$; break;
      case 6: birthDate2 = (java.lang.Long)value$; break;
      case 7: deleted2 = (java.lang.CharSequence)value$; break;
      default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
}
