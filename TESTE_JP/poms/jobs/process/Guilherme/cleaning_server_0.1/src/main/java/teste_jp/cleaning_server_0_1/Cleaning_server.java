
package teste_jp.cleaning_server_0_1;

import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.DataQuality;
import routines.Relational;
import routines.Mathematical;
import routines.DataQualityDependencies;
import routines.SQLike;
import routines.Numeric;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.DQTechnical;
import routines.TalendDate;
import routines.DataMasking;
import routines.DqStringHandling;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Cleaning_server Purpose: <br>
 * Description: <br>
 * 
 * @author security, security
 * @version 8.0.1.20230418_1502-patch
 * @status
 */
public class Cleaning_server implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Cleaning_server.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(Cleaning_server.class);

	protected static void logIgnoredError(String message, Throwable cause) {
		log.error(message, cause);

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (folder_directory != null) {

				this.setProperty("folder_directory", folder_directory.toString());

			}

			if (folder_directory2 != null) {

				this.setProperty("folder_directory2", folder_directory2.toString());

			}

			if (file_mask != null) {

				this.setProperty("file_mask", file_mask.toString());

			}

			if (file_mask2 != null) {

				this.setProperty("file_mask2", file_mask2.toString());

			}

			if (day != null) {

				this.setProperty("day", day.toString());

			}

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

		public String folder_directory;

		public String getFolder_directory() {
			return this.folder_directory;
		}

		public String folder_directory2;

		public String getFolder_directory2() {
			return this.folder_directory2;
		}

		public String file_mask;

		public String getFile_mask() {
			return this.file_mask;
		}

		public String file_mask2;

		public String getFile_mask2() {
			return this.file_mask2;
		}

		public Integer day;

		public Integer getDay() {
			return this.day;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Cleaning_server";
	private final String projectName = "TESTE_JP";
	public Integer errorCode = null;
	private String currentComponent = "";

	private String cLabel = null;

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private final JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName,
			"_6LLuEPO2Eeu0C69wHYCB7w", "0.1");
	private org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

	private RunStat runStat = new RunStat(talendJobLog, System.getProperty("audit.interval"));

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;

		private String currentComponent = null;
		private String cLabel = null;

		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		private TalendException(Exception e, String errorComponent, String errorComponentLabel,
				final java.util.Map<String, Object> globalMap) {
			this(e, errorComponent, globalMap);
			this.cLabel = errorComponentLabel;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Cleaning_server.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Cleaning_server.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
						if (enableLogStash) {
							talendJobLog.addJobExceptionMessage(currentComponent, cLabel, null, e);
							talendJobLogProcess(globalMap);
						}
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileList_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileProperties_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileDelete_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileList_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileProperties_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileDelete_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileList_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileProperties_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileDelete_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileList_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileList_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileList_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return null;

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String size;

		public String getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 255;
		}

		public Integer sizePrecision() {
			return 2;
		}

		public String sizeDefault() {

			return null;

		}

		public String sizeComment() {

			return "";

		}

		public String sizePattern() {

			return "";

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public String mtime;

		public String getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return null;

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return "dd-MM-yyyy";

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("basename=" + basename);
			sb.append(",size=" + size);
			sb.append(",mtime=" + mtime);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return null;

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String size;

		public String getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 255;
		}

		public Integer sizePrecision() {
			return 2;
		}

		public String sizeDefault() {

			return null;

		}

		public String sizeComment() {

			return "";

		}

		public String sizePattern() {

			return "";

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public String mtime;

		public String getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return null;

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return "dd-MM-yyyy";

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("basename=" + basename);
			sb.append(",size=" + size);
			sb.append(",mtime=" + mtime);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return null;

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String size;

		public String getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 255;
		}

		public Integer sizePrecision() {
			return 2;
		}

		public String sizeDefault() {

			return null;

		}

		public String sizeComment() {

			return "";

		}

		public String sizePattern() {

			return "";

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public String mtime;

		public String getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return null;

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return "dd-MM-yyyy";

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("basename=" + basename);
			sb.append(",size=" + size);
			sb.append(",mtime=" + mtime);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String abs_path;

		public String getAbs_path() {
			return this.abs_path;
		}

		public Boolean abs_pathIsNullable() {
			return true;
		}

		public Boolean abs_pathIsKey() {
			return false;
		}

		public Integer abs_pathLength() {
			return 255;
		}

		public Integer abs_pathPrecision() {
			return 0;
		}

		public String abs_pathDefault() {

			return "";

		}

		public String abs_pathComment() {

			return null;

		}

		public String abs_pathPattern() {

			return null;

		}

		public String abs_pathOriginalDbColumnName() {

			return "abs_path";

		}

		public String dirname;

		public String getDirname() {
			return this.dirname;
		}

		public Boolean dirnameIsNullable() {
			return true;
		}

		public Boolean dirnameIsKey() {
			return false;
		}

		public Integer dirnameLength() {
			return 255;
		}

		public Integer dirnamePrecision() {
			return 0;
		}

		public String dirnameDefault() {

			return "";

		}

		public String dirnameComment() {

			return null;

		}

		public String dirnamePattern() {

			return null;

		}

		public String dirnameOriginalDbColumnName() {

			return "dirname";

		}

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return "";

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String mode_string;

		public String getMode_string() {
			return this.mode_string;
		}

		public Boolean mode_stringIsNullable() {
			return true;
		}

		public Boolean mode_stringIsKey() {
			return false;
		}

		public Integer mode_stringLength() {
			return 10;
		}

		public Integer mode_stringPrecision() {
			return 0;
		}

		public String mode_stringDefault() {

			return "";

		}

		public String mode_stringComment() {

			return null;

		}

		public String mode_stringPattern() {

			return null;

		}

		public String mode_stringOriginalDbColumnName() {

			return "mode_string";

		}

		public Long size;

		public Long getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 20;
		}

		public Integer sizePrecision() {
			return 0;
		}

		public String sizeDefault() {

			return "";

		}

		public String sizeComment() {

			return null;

		}

		public String sizePattern() {

			return null;

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public Long mtime;

		public Long getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return "";

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return null;

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		public String mtime_string;

		public String getMtime_string() {
			return this.mtime_string;
		}

		public Boolean mtime_stringIsNullable() {
			return true;
		}

		public Boolean mtime_stringIsKey() {
			return false;
		}

		public Integer mtime_stringLength() {
			return 20;
		}

		public Integer mtime_stringPrecision() {
			return 0;
		}

		public String mtime_stringDefault() {

			return "";

		}

		public String mtime_stringComment() {

			return null;

		}

		public String mtime_stringPattern() {

			return null;

		}

		public String mtime_stringOriginalDbColumnName() {

			return "mtime_string";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.abs_path = readString(dis);

					this.dirname = readString(dis);

					this.basename = readString(dis);

					this.mode_string = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.size = null;
					} else {
						this.size = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.mtime = null;
					} else {
						this.mtime = dis.readLong();
					}

					this.mtime_string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.abs_path = readString(dis);

					this.dirname = readString(dis);

					this.basename = readString(dis);

					this.mode_string = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.size = null;
					} else {
						this.size = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.mtime = null;
					} else {
						this.mtime = dis.readLong();
					}

					this.mtime_string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.abs_path, dos);

				// String

				writeString(this.dirname, dos);

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.mode_string, dos);

				// Long

				if (this.size == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.size);
				}

				// Long

				if (this.mtime == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.mtime);
				}

				// String

				writeString(this.mtime_string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.abs_path, dos);

				// String

				writeString(this.dirname, dos);

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.mode_string, dos);

				// Long

				if (this.size == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.size);
				}

				// Long

				if (this.mtime == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.mtime);
				}

				// String

				writeString(this.mtime_string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("abs_path=" + abs_path);
			sb.append(",dirname=" + dirname);
			sb.append(",basename=" + basename);
			sb.append(",mode_string=" + mode_string);
			sb.append(",size=" + String.valueOf(size));
			sb.append(",mtime=" + String.valueOf(mtime));
			sb.append(",mtime_string=" + mtime_string);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (abs_path == null) {
				sb.append("<null>");
			} else {
				sb.append(abs_path);
			}

			sb.append("|");

			if (dirname == null) {
				sb.append("<null>");
			} else {
				sb.append(dirname);
			}

			sb.append("|");

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (mode_string == null) {
				sb.append("<null>");
			} else {
				sb.append(mode_string);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			if (mtime_string == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime_string);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileList_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileList_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tFileList_1");
		org.slf4j.MDC.put("_subJobPid", TalendString.getAsciiRandomString(6));

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				out1Struct out1 = new out1Struct();
				out1Struct row3 = out1;
				out1Struct row2 = out1;

				/**
				 * [tFileList_1 begin ] start
				 */

				int NB_ITERATE_tFileProperties_1 = 0; // for statistics

				ok_Hash.put("tFileList_1", false);
				start_Hash.put("tFileList_1", System.currentTimeMillis());

				currentComponent = "tFileList_1";

				int tos_count_tFileList_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileList_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileList_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileList_1 = new StringBuilder();
							log4jParamters_tFileList_1.append("Parameters:");
							log4jParamters_tFileList_1.append("DIRECTORY" + " = " + "context.folder_directory");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("LIST_MODE" + " = " + "FILES");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("INCLUDSUBDIR" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("CASE_SENSITIVE" + " = " + "YES");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("ERROR" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("GLOBEXPRESSIONS" + " = " + "true");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1
									.append("FILES" + " = " + "[{FILEMASK=" + ("context.file_mask") + "}]");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("ORDER_BY_NOTHING" + " = " + "true");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("ORDER_BY_FILENAME" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("ORDER_BY_FILESIZE" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("ORDER_BY_MODIFIEDDATE" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("ORDER_ACTION_ASC" + " = " + "true");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("ORDER_ACTION_DESC" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("IFEXCLUDE" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("FORMAT_FILEPATH_TO_SLASH" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileList_1 - " + (log4jParamters_tFileList_1));
						}
					}
					new BytesLimit65535_tFileList_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileList_1", "tFileList_1", "tFileList");
					talendJobLogProcess(globalMap);
				}

				final StringBuffer log4jSb_tFileList_1 = new StringBuffer();

				String directory_tFileList_1 = context.folder_directory;
				final java.util.List<String> maskList_tFileList_1 = new java.util.ArrayList<String>();
				final java.util.List<java.util.regex.Pattern> patternList_tFileList_1 = new java.util.ArrayList<java.util.regex.Pattern>();
				maskList_tFileList_1.add(context.file_mask);
				for (final String filemask_tFileList_1 : maskList_tFileList_1) {
					String filemask_compile_tFileList_1 = filemask_tFileList_1;

					filemask_compile_tFileList_1 = org.apache.oro.text.GlobCompiler.globToPerl5(
							filemask_tFileList_1.toCharArray(), org.apache.oro.text.GlobCompiler.DEFAULT_MASK);

					java.util.regex.Pattern fileNamePattern_tFileList_1 = java.util.regex.Pattern
							.compile(filemask_compile_tFileList_1);
					patternList_tFileList_1.add(fileNamePattern_tFileList_1);
				}
				int NB_FILEtFileList_1 = 0;

				final boolean case_sensitive_tFileList_1 = true;

				log.info("tFileList_1 - Starting to search for matching entries.");

				final java.util.List<java.io.File> list_tFileList_1 = new java.util.ArrayList<java.io.File>();
				final java.util.Set<String> filePath_tFileList_1 = new java.util.HashSet<String>();
				java.io.File file_tFileList_1 = new java.io.File(directory_tFileList_1);

				file_tFileList_1.listFiles(new java.io.FilenameFilter() {
					public boolean accept(java.io.File dir, String name) {
						java.io.File file = new java.io.File(dir, name);
						if (!file.isDirectory()) {

							String fileName_tFileList_1 = file.getName();
							for (final java.util.regex.Pattern fileNamePattern_tFileList_1 : patternList_tFileList_1) {
								if (fileNamePattern_tFileList_1.matcher(fileName_tFileList_1).matches()) {
									if (!filePath_tFileList_1.contains(file.getAbsolutePath())) {
										list_tFileList_1.add(file);
										filePath_tFileList_1.add(file.getAbsolutePath());
									}
								}
							}
						}
						return true;
					}
				});
				java.util.Collections.sort(list_tFileList_1);

				log.info("tFileList_1 - Start to list files.");

				for (int i_tFileList_1 = 0; i_tFileList_1 < list_tFileList_1.size(); i_tFileList_1++) {
					java.io.File files_tFileList_1 = list_tFileList_1.get(i_tFileList_1);
					String fileName_tFileList_1 = files_tFileList_1.getName();

					String currentFileName_tFileList_1 = files_tFileList_1.getName();
					String currentFilePath_tFileList_1 = files_tFileList_1.getAbsolutePath();
					String currentFileDirectory_tFileList_1 = files_tFileList_1.getParent();
					String currentFileExtension_tFileList_1 = null;

					if (files_tFileList_1.getName().contains(".") && files_tFileList_1.isFile()) {
						currentFileExtension_tFileList_1 = files_tFileList_1.getName()
								.substring(files_tFileList_1.getName().lastIndexOf(".") + 1);
					} else {
						currentFileExtension_tFileList_1 = "";
					}

					NB_FILEtFileList_1++;
					globalMap.put("tFileList_1_CURRENT_FILE", currentFileName_tFileList_1);
					globalMap.put("tFileList_1_CURRENT_FILEPATH", currentFilePath_tFileList_1);
					globalMap.put("tFileList_1_CURRENT_FILEDIRECTORY", currentFileDirectory_tFileList_1);
					globalMap.put("tFileList_1_CURRENT_FILEEXTENSION", currentFileExtension_tFileList_1);
					globalMap.put("tFileList_1_NB_FILE", NB_FILEtFileList_1);

					log.info("tFileList_1 - Current file or directory path : " + currentFilePath_tFileList_1);

					/**
					 * [tFileList_1 begin ] stop
					 */

					/**
					 * [tFileList_1 main ] start
					 */

					currentComponent = "tFileList_1";

					tos_count_tFileList_1++;

					/**
					 * [tFileList_1 main ] stop
					 */

					/**
					 * [tFileList_1 process_data_begin ] start
					 */

					currentComponent = "tFileList_1";

					/**
					 * [tFileList_1 process_data_begin ] stop
					 */
					NB_ITERATE_tFileProperties_1++;

					if (execStat) {
						runStat.updateStatOnConnection("out1", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row3", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row2", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row1", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate1", 1, "exec" + NB_ITERATE_tFileProperties_1);
						// Thread.sleep(1000);
					}

					/**
					 * [tLogRow_2 begin ] start
					 */

					ok_Hash.put("tLogRow_2", false);
					start_Hash.put("tLogRow_2", System.currentTimeMillis());

					currentComponent = "tLogRow_2";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row2");

					int tos_count_tLogRow_2 = 0;

					if (log.isDebugEnabled())
						log.debug("tLogRow_2 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tLogRow_2 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tLogRow_2 = new StringBuilder();
								log4jParamters_tLogRow_2.append("Parameters:");
								log4jParamters_tLogRow_2.append("BASIC_MODE" + " = " + "false");
								log4jParamters_tLogRow_2.append(" | ");
								log4jParamters_tLogRow_2.append("TABLE_PRINT" + " = " + "true");
								log4jParamters_tLogRow_2.append(" | ");
								log4jParamters_tLogRow_2.append("VERTICAL" + " = " + "false");
								log4jParamters_tLogRow_2.append(" | ");
								log4jParamters_tLogRow_2.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
								log4jParamters_tLogRow_2.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tLogRow_2 - " + (log4jParamters_tLogRow_2));
							}
						}
						new BytesLimit65535_tLogRow_2().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tLogRow_2", "tLogRow_2", "tLogRow");
						talendJobLogProcess(globalMap);
					}

					///////////////////////

					class Util_tLogRow_2 {

						String[] des_top = { ".", ".", "-", "+" };

						String[] des_head = { "|=", "=|", "-", "+" };

						String[] des_bottom = { "'", "'", "-", "+" };

						String name = "";

						java.util.List<String[]> list = new java.util.ArrayList<String[]>();

						int[] colLengths = new int[3];

						public void addRow(String[] row) {

							for (int i = 0; i < 3; i++) {
								if (row[i] != null) {
									colLengths[i] = Math.max(colLengths[i], row[i].length());
								}
							}
							list.add(row);
						}

						public void setTableName(String name) {

							this.name = name;
						}

						public StringBuilder format() {

							StringBuilder sb = new StringBuilder();

							sb.append(print(des_top));

							int totals = 0;
							for (int i = 0; i < colLengths.length; i++) {
								totals = totals + colLengths[i];
							}

							// name
							sb.append("|");
							int k = 0;
							for (k = 0; k < (totals + 2 - name.length()) / 2; k++) {
								sb.append(' ');
							}
							sb.append(name);
							for (int i = 0; i < totals + 2 - name.length() - k; i++) {
								sb.append(' ');
							}
							sb.append("|\n");

							// head and rows
							sb.append(print(des_head));
							for (int i = 0; i < list.size(); i++) {

								String[] row = list.get(i);

								java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

								StringBuilder sbformat = new StringBuilder();
								sbformat.append("|%1$-");
								sbformat.append(colLengths[0]);
								sbformat.append("s");

								sbformat.append("|%2$-");
								sbformat.append(colLengths[1]);
								sbformat.append("s");

								sbformat.append("|%3$-");
								sbformat.append(colLengths[2]);
								sbformat.append("s");

								sbformat.append("|\n");

								formatter.format(sbformat.toString(), (Object[]) row);

								sb.append(formatter.toString());
								if (i == 0)
									sb.append(print(des_head)); // print the head
							}

							// end
							sb.append(print(des_bottom));
							return sb;
						}

						private StringBuilder print(String[] fillChars) {
							StringBuilder sb = new StringBuilder();
							// first column
							sb.append(fillChars[0]);
							for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							// last column
							for (int i = 0; i < colLengths[2] - fillChars[1].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[1]);
							sb.append("\n");
							return sb;
						}

						public boolean isTableEmpty() {
							if (list.size() > 1)
								return false;
							return true;
						}
					}
					Util_tLogRow_2 util_tLogRow_2 = new Util_tLogRow_2();
					util_tLogRow_2.setTableName("tLogRow_2");
					util_tLogRow_2.addRow(new String[] { "basename", "size", "mtime", });
					StringBuilder strBuffer_tLogRow_2 = null;
					int nb_line_tLogRow_2 = 0;
///////////////////////    			

					/**
					 * [tLogRow_2 begin ] stop
					 */

					/**
					 * [tFileDelete_1 begin ] start
					 */

					ok_Hash.put("tFileDelete_1", false);
					start_Hash.put("tFileDelete_1", System.currentTimeMillis());

					currentComponent = "tFileDelete_1";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

					int tos_count_tFileDelete_1 = 0;

					if (log.isDebugEnabled())
						log.debug("tFileDelete_1 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tFileDelete_1 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tFileDelete_1 = new StringBuilder();
								log4jParamters_tFileDelete_1.append("Parameters:");
								log4jParamters_tFileDelete_1.append("FILENAME" + " = "
										+ "((String)globalMap.get(\"tFileList_1_CURRENT_FILEPATH\"))");
								log4jParamters_tFileDelete_1.append(" | ");
								log4jParamters_tFileDelete_1.append("FAILON" + " = " + "false");
								log4jParamters_tFileDelete_1.append(" | ");
								log4jParamters_tFileDelete_1.append("FOLDER" + " = " + "false");
								log4jParamters_tFileDelete_1.append(" | ");
								log4jParamters_tFileDelete_1.append("FOLDER_FILE" + " = " + "false");
								log4jParamters_tFileDelete_1.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tFileDelete_1 - " + (log4jParamters_tFileDelete_1));
							}
						}
						new BytesLimit65535_tFileDelete_1().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tFileDelete_1", "tFileDelete_1", "tFileDelete");
						talendJobLogProcess(globalMap);
					}

					/**
					 * [tFileDelete_1 begin ] stop
					 */

					/**
					 * [tLogRow_1 begin ] start
					 */

					ok_Hash.put("tLogRow_1", false);
					start_Hash.put("tLogRow_1", System.currentTimeMillis());

					currentComponent = "tLogRow_1";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out1");

					int tos_count_tLogRow_1 = 0;

					if (log.isDebugEnabled())
						log.debug("tLogRow_1 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tLogRow_1 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
								log4jParamters_tLogRow_1.append("Parameters:");
								log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "false");
								log4jParamters_tLogRow_1.append(" | ");
								log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "true");
								log4jParamters_tLogRow_1.append(" | ");
								log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
								log4jParamters_tLogRow_1.append(" | ");
								log4jParamters_tLogRow_1.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
								log4jParamters_tLogRow_1.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tLogRow_1 - " + (log4jParamters_tLogRow_1));
							}
						}
						new BytesLimit65535_tLogRow_1().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tLogRow_1", "tLogRow_1", "tLogRow");
						talendJobLogProcess(globalMap);
					}

					///////////////////////

					class Util_tLogRow_1 {

						String[] des_top = { ".", ".", "-", "+" };

						String[] des_head = { "|=", "=|", "-", "+" };

						String[] des_bottom = { "'", "'", "-", "+" };

						String name = "";

						java.util.List<String[]> list = new java.util.ArrayList<String[]>();

						int[] colLengths = new int[3];

						public void addRow(String[] row) {

							for (int i = 0; i < 3; i++) {
								if (row[i] != null) {
									colLengths[i] = Math.max(colLengths[i], row[i].length());
								}
							}
							list.add(row);
						}

						public void setTableName(String name) {

							this.name = name;
						}

						public StringBuilder format() {

							StringBuilder sb = new StringBuilder();

							sb.append(print(des_top));

							int totals = 0;
							for (int i = 0; i < colLengths.length; i++) {
								totals = totals + colLengths[i];
							}

							// name
							sb.append("|");
							int k = 0;
							for (k = 0; k < (totals + 2 - name.length()) / 2; k++) {
								sb.append(' ');
							}
							sb.append(name);
							for (int i = 0; i < totals + 2 - name.length() - k; i++) {
								sb.append(' ');
							}
							sb.append("|\n");

							// head and rows
							sb.append(print(des_head));
							for (int i = 0; i < list.size(); i++) {

								String[] row = list.get(i);

								java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

								StringBuilder sbformat = new StringBuilder();
								sbformat.append("|%1$-");
								sbformat.append(colLengths[0]);
								sbformat.append("s");

								sbformat.append("|%2$-");
								sbformat.append(colLengths[1]);
								sbformat.append("s");

								sbformat.append("|%3$-");
								sbformat.append(colLengths[2]);
								sbformat.append("s");

								sbformat.append("|\n");

								formatter.format(sbformat.toString(), (Object[]) row);

								sb.append(formatter.toString());
								if (i == 0)
									sb.append(print(des_head)); // print the head
							}

							// end
							sb.append(print(des_bottom));
							return sb;
						}

						private StringBuilder print(String[] fillChars) {
							StringBuilder sb = new StringBuilder();
							// first column
							sb.append(fillChars[0]);
							for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							// last column
							for (int i = 0; i < colLengths[2] - fillChars[1].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[1]);
							sb.append("\n");
							return sb;
						}

						public boolean isTableEmpty() {
							if (list.size() > 1)
								return false;
							return true;
						}
					}
					Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
					util_tLogRow_1.setTableName("tLogRow_1");
					util_tLogRow_1.addRow(new String[] { "basename", "size", "mtime", });
					StringBuilder strBuffer_tLogRow_1 = null;
					int nb_line_tLogRow_1 = 0;
///////////////////////    			

					/**
					 * [tLogRow_1 begin ] stop
					 */

					/**
					 * [tMap_1 begin ] start
					 */

					ok_Hash.put("tMap_1", false);
					start_Hash.put("tMap_1", System.currentTimeMillis());

					currentComponent = "tMap_1";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

					int tos_count_tMap_1 = 0;

					if (log.isDebugEnabled())
						log.debug("tMap_1 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tMap_1 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tMap_1 = new StringBuilder();
								log4jParamters_tMap_1.append("Parameters:");
								log4jParamters_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
								log4jParamters_tMap_1.append(" | ");
								log4jParamters_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
								log4jParamters_tMap_1.append(" | ");
								log4jParamters_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
								log4jParamters_tMap_1.append(" | ");
								log4jParamters_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
								log4jParamters_tMap_1.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tMap_1 - " + (log4jParamters_tMap_1));
							}
						}
						new BytesLimit65535_tMap_1().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tMap_1", "tMap_1", "tMap");
						talendJobLogProcess(globalMap);
					}

// ###############################
// # Lookup's keys initialization
					int count_row1_tMap_1 = 0;

// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_1__Struct {
					}
					Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
					int count_out1_tMap_1 = 0;

					out1Struct out1_tmp = new out1Struct();
// ###############################

					/**
					 * [tMap_1 begin ] stop
					 */

					/**
					 * [tFileProperties_1 begin ] start
					 */

					ok_Hash.put("tFileProperties_1", false);
					start_Hash.put("tFileProperties_1", System.currentTimeMillis());

					currentComponent = "tFileProperties_1";

					int tos_count_tFileProperties_1 = 0;

					if (log.isDebugEnabled())
						log.debug("tFileProperties_1 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tFileProperties_1 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tFileProperties_1 = new StringBuilder();
								log4jParamters_tFileProperties_1.append("Parameters:");
								log4jParamters_tFileProperties_1.append("FILENAME" + " = "
										+ "((String)globalMap.get(\"tFileList_1_CURRENT_FILEPATH\"))");
								log4jParamters_tFileProperties_1.append(" | ");
								log4jParamters_tFileProperties_1.append("MD5" + " = " + "false");
								log4jParamters_tFileProperties_1.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tFileProperties_1 - " + (log4jParamters_tFileProperties_1));
							}
						}
						new BytesLimit65535_tFileProperties_1().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tFileProperties_1", "tFileProperties_1", "tFileProperties");
						talendJobLogProcess(globalMap);
					}

					final StringBuffer log4jSb_tFileProperties_1 = new StringBuffer();

					java.io.File file_tFileProperties_1 = new java.io.File(
							((String) globalMap.get("tFileList_1_CURRENT_FILEPATH")));
					row1 = new row1Struct();

					if (file_tFileProperties_1.exists()) {
						row1.abs_path = file_tFileProperties_1.getAbsolutePath();
						row1.dirname = file_tFileProperties_1.getParent();
						row1.basename = file_tFileProperties_1.getName();
						String r_tFileProperties_1 = (file_tFileProperties_1.canRead()) ? "r" : "-";
						String w_tFileProperties_1 = (file_tFileProperties_1.canWrite()) ? "w" : "-";
						// String x_ = (file_.canExecute())?"x":"-"; /*since JDK1.6*/
						row1.mode_string = r_tFileProperties_1 + w_tFileProperties_1;
						row1.size = file_tFileProperties_1.length();
						row1.mtime = file_tFileProperties_1.lastModified();
						row1.mtime_string = (new java.util.Date(file_tFileProperties_1.lastModified())).toString();

					} else {
						log.info("tFileProperties_1 - File : " + file_tFileProperties_1.getAbsolutePath()
								+ " doesn't exist.");
					}

					/**
					 * [tFileProperties_1 begin ] stop
					 */

					/**
					 * [tFileProperties_1 main ] start
					 */

					currentComponent = "tFileProperties_1";

					tos_count_tFileProperties_1++;

					/**
					 * [tFileProperties_1 main ] stop
					 */

					/**
					 * [tFileProperties_1 process_data_begin ] start
					 */

					currentComponent = "tFileProperties_1";

					/**
					 * [tFileProperties_1 process_data_begin ] stop
					 */

					/**
					 * [tMap_1 main ] start
					 */

					currentComponent = "tMap_1";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row1", "tFileProperties_1", "tFileProperties_1", "tFileProperties", "tMap_1", "tMap_1",
							"tMap"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
					}

					boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

					// ###############################
					// # Input tables (lookups)

					boolean rejectedInnerJoin_tMap_1 = false;
					boolean mainRowRejected_tMap_1 = false;

					if (

					(

					(TalendDate.getCurrentDate().getTime() - row1.mtime) / (24 * 60 * 60 * 1000) >= 2

					)

					) { // G_TM_M_280

						// CALL close main tMap filter for table 'row1'
						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
							// ###############################
							// # Output tables

							out1 = null;

// # Output table : 'out1'
							count_out1_tMap_1++;

							out1_tmp.basename = row1.basename;
							out1_tmp.size = ((row1.size) / (1000000)) + " mb";
							out1_tmp.mtime = ((TalendDate.getCurrentDate().getTime() - row1.mtime)
									/ (24 * 60 * 60 * 1000)) + " dias";
							out1 = out1_tmp;
							log.debug("tMap_1 - Outputting the record " + count_out1_tMap_1
									+ " of the output table 'out1'.");

// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_1 = false;

						tos_count_tMap_1++;

						/**
						 * [tMap_1 main ] stop
						 */

						/**
						 * [tMap_1 process_data_begin ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_begin ] stop
						 */
// Start of branch "out1"
						if (out1 != null) {

							/**
							 * [tLogRow_1 main ] start
							 */

							currentComponent = "tLogRow_1";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "out1", "tMap_1", "tMap_1", "tMap", "tLogRow_1", "tLogRow_1", "tLogRow"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("out1 - " + (out1 == null ? "" : out1.toLogString()));
							}

///////////////////////		

							String[] row_tLogRow_1 = new String[3];

							if (out1.basename != null) { //
								row_tLogRow_1[0] = String.valueOf(out1.basename);

							} //

							if (out1.size != null) { //
								row_tLogRow_1[1] = String.valueOf(out1.size);

							} //

							if (out1.mtime != null) { //
								row_tLogRow_1[2] = String.valueOf(out1.mtime);

							} //

							util_tLogRow_1.addRow(row_tLogRow_1);
							nb_line_tLogRow_1++;
							log.info("tLogRow_1 - Content of row " + nb_line_tLogRow_1 + ": "
									+ TalendString.unionString("|", row_tLogRow_1));
//////

//////                    

///////////////////////    			

							row3 = out1;

							tos_count_tLogRow_1++;

							/**
							 * [tLogRow_1 main ] stop
							 */

							/**
							 * [tLogRow_1 process_data_begin ] start
							 */

							currentComponent = "tLogRow_1";

							/**
							 * [tLogRow_1 process_data_begin ] stop
							 */

							/**
							 * [tFileDelete_1 main ] start
							 */

							currentComponent = "tFileDelete_1";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row3", "tLogRow_1", "tLogRow_1", "tLogRow", "tFileDelete_1", "tFileDelete_1",
									"tFileDelete"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
							}

							final StringBuffer log4jSb_tFileDelete_1 = new StringBuffer();

							class DeleteFoldertFileDelete_1 {
								/**
								 * delete all the sub-files in 'file'
								 * 
								 * @param file
								 */
								public boolean delete(java.io.File file) {
									java.io.File[] files = file.listFiles();
									for (int i = 0; i < files.length; i++) {
										if (files[i].isFile()) {
											files[i].delete();
										} else if (files[i].isDirectory()) {
											if (!files[i].delete()) {
												delete(files[i]);
											}
										}
									}
									deleteDirectory(file);
									return file.delete();
								}

								/**
								 * delete all the sub-folders in 'file'
								 * 
								 * @param file
								 */
								private void deleteDirectory(java.io.File file) {
									java.io.File[] filed = file.listFiles();
									for (int i = 0; i < filed.length; i++) {
										if (filed[i].isDirectory()) {
											deleteDirectory(filed[i]);
										}
										filed[i].delete();
									}
								}

							}
							java.io.File file_tFileDelete_1 = new java.io.File(
									((String) globalMap.get("tFileList_1_CURRENT_FILEPATH")));
							if (file_tFileDelete_1.exists() && file_tFileDelete_1.isFile()) {
								if (file_tFileDelete_1.delete()) {
									globalMap.put("tFileDelete_1_CURRENT_STATUS", "File deleted.");
									log.info("tFileDelete_1 - File : " + file_tFileDelete_1.getAbsolutePath()
											+ " is deleted.");
								} else {
									globalMap.put("tFileDelete_1_CURRENT_STATUS", "No file deleted.");
									log.error("tFileDelete_1 - Fail to delete file : "
											+ file_tFileDelete_1.getAbsolutePath());
									globalMap.put("tFileDelete_1_ERROR_MESSAGE",
											"File " + file_tFileDelete_1.getAbsolutePath() + " can not be deleted.");
								}
							} else {
								globalMap.put("tFileDelete_1_CURRENT_STATUS", "File does not exist or is invalid.");
								log.error("tFileDelete_1 - " + file_tFileDelete_1.getAbsolutePath()
										+ " does not exist or is invalid or is not a file.");
								globalMap.put("tFileDelete_1_ERROR_MESSAGE",
										"File " + file_tFileDelete_1.getAbsolutePath()
												+ " does not exist or is invalid or is not a file.");
							}
							globalMap.put("tFileDelete_1_DELETE_PATH",
									((String) globalMap.get("tFileList_1_CURRENT_FILEPATH")));

							row2 = row3;

							tos_count_tFileDelete_1++;

							/**
							 * [tFileDelete_1 main ] stop
							 */

							/**
							 * [tFileDelete_1 process_data_begin ] start
							 */

							currentComponent = "tFileDelete_1";

							/**
							 * [tFileDelete_1 process_data_begin ] stop
							 */

							/**
							 * [tLogRow_2 main ] start
							 */

							currentComponent = "tLogRow_2";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row2", "tFileDelete_1", "tFileDelete_1", "tFileDelete", "tLogRow_2", "tLogRow_2",
									"tLogRow"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row2 - " + (row2 == null ? "" : row2.toLogString()));
							}

///////////////////////		

							String[] row_tLogRow_2 = new String[3];

							if (row2.basename != null) { //
								row_tLogRow_2[0] = String.valueOf(row2.basename);

							} //

							if (row2.size != null) { //
								row_tLogRow_2[1] = String.valueOf(row2.size);

							} //

							if (row2.mtime != null) { //
								row_tLogRow_2[2] = String.valueOf(row2.mtime);

							} //

							util_tLogRow_2.addRow(row_tLogRow_2);
							nb_line_tLogRow_2++;
							log.info("tLogRow_2 - Content of row " + nb_line_tLogRow_2 + ": "
									+ TalendString.unionString("|", row_tLogRow_2));
//////

//////                    

///////////////////////    			

							tos_count_tLogRow_2++;

							/**
							 * [tLogRow_2 main ] stop
							 */

							/**
							 * [tLogRow_2 process_data_begin ] start
							 */

							currentComponent = "tLogRow_2";

							/**
							 * [tLogRow_2 process_data_begin ] stop
							 */

							/**
							 * [tLogRow_2 process_data_end ] start
							 */

							currentComponent = "tLogRow_2";

							/**
							 * [tLogRow_2 process_data_end ] stop
							 */

							/**
							 * [tFileDelete_1 process_data_end ] start
							 */

							currentComponent = "tFileDelete_1";

							/**
							 * [tFileDelete_1 process_data_end ] stop
							 */

							/**
							 * [tLogRow_1 process_data_end ] start
							 */

							currentComponent = "tLogRow_1";

							/**
							 * [tLogRow_1 process_data_end ] stop
							 */

						} // End of branch "out1"

					} // G_TM_M_280 close main tMap filter for table 'row1'

					/**
					 * [tMap_1 process_data_end ] start
					 */

					currentComponent = "tMap_1";

					/**
					 * [tMap_1 process_data_end ] stop
					 */

					/**
					 * [tFileProperties_1 process_data_end ] start
					 */

					currentComponent = "tFileProperties_1";

					/**
					 * [tFileProperties_1 process_data_end ] stop
					 */

					/**
					 * [tFileProperties_1 end ] start
					 */

					currentComponent = "tFileProperties_1";

					if (log.isDebugEnabled())
						log.debug("tFileProperties_1 - " + ("Done."));

					ok_Hash.put("tFileProperties_1", true);
					end_Hash.put("tFileProperties_1", System.currentTimeMillis());

					/**
					 * [tFileProperties_1 end ] stop
					 */

					/**
					 * [tMap_1 end ] start
					 */

					currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      
					log.debug("tMap_1 - Written records count in the table 'out1': " + count_out1_tMap_1 + ".");

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
							"tFileProperties_1", "tFileProperties_1", "tFileProperties", "tMap_1", "tMap_1", "tMap",
							"output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tMap_1 - " + ("Done."));

					ok_Hash.put("tMap_1", true);
					end_Hash.put("tMap_1", System.currentTimeMillis());

					/**
					 * [tMap_1 end ] stop
					 */

					/**
					 * [tLogRow_1 end ] start
					 */

					currentComponent = "tLogRow_1";

//////

					java.io.PrintStream consoleOut_tLogRow_1 = null;
					if (globalMap.get("tLogRow_CONSOLE") != null) {
						consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
					} else {
						consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
						globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
					}

					consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
					consoleOut_tLogRow_1.flush();
//////
					globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);
					if (log.isInfoEnabled())
						log.info("tLogRow_1 - " + ("Printed row count: ") + (nb_line_tLogRow_1) + ("."));

///////////////////////    			

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out1", 2, 0,
							"tMap_1", "tMap_1", "tMap", "tLogRow_1", "tLogRow_1", "tLogRow", "output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tLogRow_1 - " + ("Done."));

					ok_Hash.put("tLogRow_1", true);
					end_Hash.put("tLogRow_1", System.currentTimeMillis());

					/**
					 * [tLogRow_1 end ] stop
					 */

					/**
					 * [tFileDelete_1 end ] start
					 */

					currentComponent = "tFileDelete_1";

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
							"tLogRow_1", "tLogRow_1", "tLogRow", "tFileDelete_1", "tFileDelete_1", "tFileDelete",
							"output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tFileDelete_1 - " + ("Done."));

					ok_Hash.put("tFileDelete_1", true);
					end_Hash.put("tFileDelete_1", System.currentTimeMillis());

					/**
					 * [tFileDelete_1 end ] stop
					 */

					/**
					 * [tLogRow_2 end ] start
					 */

					currentComponent = "tLogRow_2";

//////

					java.io.PrintStream consoleOut_tLogRow_2 = null;
					if (globalMap.get("tLogRow_CONSOLE") != null) {
						consoleOut_tLogRow_2 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
					} else {
						consoleOut_tLogRow_2 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
						globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_2);
					}

					consoleOut_tLogRow_2.println(util_tLogRow_2.format().toString());
					consoleOut_tLogRow_2.flush();
//////
					globalMap.put("tLogRow_2_NB_LINE", nb_line_tLogRow_2);
					if (log.isInfoEnabled())
						log.info("tLogRow_2 - " + ("Printed row count: ") + (nb_line_tLogRow_2) + ("."));

///////////////////////    			

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row2", 2, 0,
							"tFileDelete_1", "tFileDelete_1", "tFileDelete", "tLogRow_2", "tLogRow_2", "tLogRow",
							"output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tLogRow_2 - " + ("Done."));

					ok_Hash.put("tLogRow_2", true);
					end_Hash.put("tLogRow_2", System.currentTimeMillis());

					/**
					 * [tLogRow_2 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate1", 2, "exec" + NB_ITERATE_tFileProperties_1);
					}

					/**
					 * [tFileList_1 process_data_end ] start
					 */

					currentComponent = "tFileList_1";

					/**
					 * [tFileList_1 process_data_end ] stop
					 */

					/**
					 * [tFileList_1 end ] start
					 */

					currentComponent = "tFileList_1";

				}
				globalMap.put("tFileList_1_NB_FILE", NB_FILEtFileList_1);

				log.info("tFileList_1 - File or directory count : " + NB_FILEtFileList_1);

				if (log.isDebugEnabled())
					log.debug("tFileList_1 - " + ("Done."));

				ok_Hash.put("tFileList_1", true);
				end_Hash.put("tFileList_1", System.currentTimeMillis());

				/**
				 * [tFileList_1 end ] stop
				 */
			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileList_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
			}

			tFileList_2Process(globalMap);

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileList_1 finally ] start
				 */

				currentComponent = "tFileList_1";

				/**
				 * [tFileList_1 finally ] stop
				 */

				/**
				 * [tFileProperties_1 finally ] start
				 */

				currentComponent = "tFileProperties_1";

				/**
				 * [tFileProperties_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tFileDelete_1 finally ] start
				 */

				currentComponent = "tFileDelete_1";

				/**
				 * [tFileDelete_1 finally ] stop
				 */

				/**
				 * [tLogRow_2 finally ] start
				 */

				currentComponent = "tLogRow_2";

				/**
				 * [tLogRow_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileList_1_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return null;

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String size;

		public String getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 255;
		}

		public Integer sizePrecision() {
			return 2;
		}

		public String sizeDefault() {

			return null;

		}

		public String sizeComment() {

			return "";

		}

		public String sizePattern() {

			return "";

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public String mtime;

		public String getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return null;

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return "dd-MM-yyyy";

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("basename=" + basename);
			sb.append(",size=" + size);
			sb.append(",mtime=" + mtime);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return null;

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String size;

		public String getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 255;
		}

		public Integer sizePrecision() {
			return 2;
		}

		public String sizeDefault() {

			return null;

		}

		public String sizeComment() {

			return "";

		}

		public String sizePattern() {

			return "";

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public String mtime;

		public String getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return null;

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return "dd-MM-yyyy";

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("basename=" + basename);
			sb.append(",size=" + size);
			sb.append(",mtime=" + mtime);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class copyOfout1Struct implements routines.system.IPersistableRow<copyOfout1Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return null;

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String size;

		public String getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 255;
		}

		public Integer sizePrecision() {
			return 2;
		}

		public String sizeDefault() {

			return null;

		}

		public String sizeComment() {

			return "";

		}

		public String sizePattern() {

			return "";

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public String mtime;

		public String getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return null;

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return "dd-MM-yyyy";

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("basename=" + basename);
			sb.append(",size=" + size);
			sb.append(",mtime=" + mtime);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(copyOfout1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String abs_path;

		public String getAbs_path() {
			return this.abs_path;
		}

		public Boolean abs_pathIsNullable() {
			return true;
		}

		public Boolean abs_pathIsKey() {
			return false;
		}

		public Integer abs_pathLength() {
			return 255;
		}

		public Integer abs_pathPrecision() {
			return 0;
		}

		public String abs_pathDefault() {

			return "";

		}

		public String abs_pathComment() {

			return null;

		}

		public String abs_pathPattern() {

			return null;

		}

		public String abs_pathOriginalDbColumnName() {

			return "abs_path";

		}

		public String dirname;

		public String getDirname() {
			return this.dirname;
		}

		public Boolean dirnameIsNullable() {
			return true;
		}

		public Boolean dirnameIsKey() {
			return false;
		}

		public Integer dirnameLength() {
			return 255;
		}

		public Integer dirnamePrecision() {
			return 0;
		}

		public String dirnameDefault() {

			return "";

		}

		public String dirnameComment() {

			return null;

		}

		public String dirnamePattern() {

			return null;

		}

		public String dirnameOriginalDbColumnName() {

			return "dirname";

		}

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return "";

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String mode_string;

		public String getMode_string() {
			return this.mode_string;
		}

		public Boolean mode_stringIsNullable() {
			return true;
		}

		public Boolean mode_stringIsKey() {
			return false;
		}

		public Integer mode_stringLength() {
			return 10;
		}

		public Integer mode_stringPrecision() {
			return 0;
		}

		public String mode_stringDefault() {

			return "";

		}

		public String mode_stringComment() {

			return null;

		}

		public String mode_stringPattern() {

			return null;

		}

		public String mode_stringOriginalDbColumnName() {

			return "mode_string";

		}

		public Long size;

		public Long getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 20;
		}

		public Integer sizePrecision() {
			return 0;
		}

		public String sizeDefault() {

			return "";

		}

		public String sizeComment() {

			return null;

		}

		public String sizePattern() {

			return null;

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public Long mtime;

		public Long getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return "";

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return null;

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		public String mtime_string;

		public String getMtime_string() {
			return this.mtime_string;
		}

		public Boolean mtime_stringIsNullable() {
			return true;
		}

		public Boolean mtime_stringIsKey() {
			return false;
		}

		public Integer mtime_stringLength() {
			return 20;
		}

		public Integer mtime_stringPrecision() {
			return 0;
		}

		public String mtime_stringDefault() {

			return "";

		}

		public String mtime_stringComment() {

			return null;

		}

		public String mtime_stringPattern() {

			return null;

		}

		public String mtime_stringOriginalDbColumnName() {

			return "mtime_string";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.abs_path = readString(dis);

					this.dirname = readString(dis);

					this.basename = readString(dis);

					this.mode_string = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.size = null;
					} else {
						this.size = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.mtime = null;
					} else {
						this.mtime = dis.readLong();
					}

					this.mtime_string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.abs_path = readString(dis);

					this.dirname = readString(dis);

					this.basename = readString(dis);

					this.mode_string = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.size = null;
					} else {
						this.size = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.mtime = null;
					} else {
						this.mtime = dis.readLong();
					}

					this.mtime_string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.abs_path, dos);

				// String

				writeString(this.dirname, dos);

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.mode_string, dos);

				// Long

				if (this.size == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.size);
				}

				// Long

				if (this.mtime == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.mtime);
				}

				// String

				writeString(this.mtime_string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.abs_path, dos);

				// String

				writeString(this.dirname, dos);

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.mode_string, dos);

				// Long

				if (this.size == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.size);
				}

				// Long

				if (this.mtime == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.mtime);
				}

				// String

				writeString(this.mtime_string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("abs_path=" + abs_path);
			sb.append(",dirname=" + dirname);
			sb.append(",basename=" + basename);
			sb.append(",mode_string=" + mode_string);
			sb.append(",size=" + String.valueOf(size));
			sb.append(",mtime=" + String.valueOf(mtime));
			sb.append(",mtime_string=" + mtime_string);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (abs_path == null) {
				sb.append("<null>");
			} else {
				sb.append(abs_path);
			}

			sb.append("|");

			if (dirname == null) {
				sb.append("<null>");
			} else {
				sb.append(dirname);
			}

			sb.append("|");

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (mode_string == null) {
				sb.append("<null>");
			} else {
				sb.append(mode_string);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			if (mtime_string == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime_string);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileList_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileList_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tFileList_2");
		org.slf4j.MDC.put("_subJobPid", TalendString.getAsciiRandomString(6));

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row4Struct row4 = new row4Struct();
				copyOfout1Struct copyOfout1 = new copyOfout1Struct();
				copyOfout1Struct row5 = copyOfout1;
				copyOfout1Struct row6 = copyOfout1;

				/**
				 * [tFileList_2 begin ] start
				 */

				int NB_ITERATE_tFileProperties_2 = 0; // for statistics

				ok_Hash.put("tFileList_2", false);
				start_Hash.put("tFileList_2", System.currentTimeMillis());

				currentComponent = "tFileList_2";

				int tos_count_tFileList_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileList_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileList_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileList_2 = new StringBuilder();
							log4jParamters_tFileList_2.append("Parameters:");
							log4jParamters_tFileList_2.append("DIRECTORY" + " = " + "context.folder_directory");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("LIST_MODE" + " = " + "DIRECTORIES");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("INCLUDSUBDIR" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("CASE_SENSITIVE" + " = " + "NO");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("ERROR" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("GLOBEXPRESSIONS" + " = " + "true");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("FILES" + " = " + "[]");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("ORDER_BY_NOTHING" + " = " + "true");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("ORDER_BY_FILENAME" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("ORDER_BY_FILESIZE" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("ORDER_BY_MODIFIEDDATE" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("ORDER_ACTION_ASC" + " = " + "true");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("ORDER_ACTION_DESC" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("IFEXCLUDE" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("FORMAT_FILEPATH_TO_SLASH" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileList_2 - " + (log4jParamters_tFileList_2));
						}
					}
					new BytesLimit65535_tFileList_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileList_2", "tFileList_2", "tFileList");
					talendJobLogProcess(globalMap);
				}

				final StringBuffer log4jSb_tFileList_2 = new StringBuffer();

				String directory_tFileList_2 = context.folder_directory;
				final java.util.List<String> maskList_tFileList_2 = new java.util.ArrayList<String>();
				final java.util.List<java.util.regex.Pattern> patternList_tFileList_2 = new java.util.ArrayList<java.util.regex.Pattern>();
				maskList_tFileList_2.add("*");
				for (final String filemask_tFileList_2 : maskList_tFileList_2) {
					String filemask_compile_tFileList_2 = filemask_tFileList_2;

					filemask_compile_tFileList_2 = org.apache.oro.text.GlobCompiler.globToPerl5(
							filemask_tFileList_2.toCharArray(), org.apache.oro.text.GlobCompiler.DEFAULT_MASK);

					java.util.regex.Pattern fileNamePattern_tFileList_2 = java.util.regex.Pattern
							.compile(filemask_compile_tFileList_2, java.util.regex.Pattern.CASE_INSENSITIVE);

					patternList_tFileList_2.add(fileNamePattern_tFileList_2);
				}
				int NB_FILEtFileList_2 = 0;

				final boolean case_sensitive_tFileList_2 = false;

				log.info("tFileList_2 - Starting to search for matching entries.");

				final java.util.List<java.io.File> list_tFileList_2 = new java.util.ArrayList<java.io.File>();
				final java.util.Set<String> filePath_tFileList_2 = new java.util.HashSet<String>();
				java.io.File file_tFileList_2 = new java.io.File(directory_tFileList_2);

				file_tFileList_2.listFiles(new java.io.FilenameFilter() {
					public boolean accept(java.io.File dir, String name) {
						java.io.File file = new java.io.File(dir, name);
						if (file.isDirectory()) {

							String fileName_tFileList_2 = file.getName();
							for (final java.util.regex.Pattern fileNamePattern_tFileList_2 : patternList_tFileList_2) {
								if (fileNamePattern_tFileList_2.matcher(fileName_tFileList_2).matches()) {
									if (!filePath_tFileList_2.contains(file.getAbsolutePath())) {
										list_tFileList_2.add(file);
										filePath_tFileList_2.add(file.getAbsolutePath());
									}
								}
							}
						}
						return true;
					}
				});
				java.util.Collections.sort(list_tFileList_2);

				log.info("tFileList_2 - Start to list files.");

				for (int i_tFileList_2 = 0; i_tFileList_2 < list_tFileList_2.size(); i_tFileList_2++) {
					java.io.File files_tFileList_2 = list_tFileList_2.get(i_tFileList_2);
					String fileName_tFileList_2 = files_tFileList_2.getName();

					String currentFileName_tFileList_2 = files_tFileList_2.getName();
					String currentFilePath_tFileList_2 = files_tFileList_2.getAbsolutePath();
					String currentFileDirectory_tFileList_2 = files_tFileList_2.getParent();
					String currentFileExtension_tFileList_2 = null;

					if (files_tFileList_2.getName().contains(".") && files_tFileList_2.isFile()) {
						currentFileExtension_tFileList_2 = files_tFileList_2.getName()
								.substring(files_tFileList_2.getName().lastIndexOf(".") + 1);
					} else {
						currentFileExtension_tFileList_2 = "";
					}

					NB_FILEtFileList_2++;
					globalMap.put("tFileList_2_CURRENT_FILE", currentFileName_tFileList_2);
					globalMap.put("tFileList_2_CURRENT_FILEPATH", currentFilePath_tFileList_2);
					globalMap.put("tFileList_2_CURRENT_FILEDIRECTORY", currentFileDirectory_tFileList_2);
					globalMap.put("tFileList_2_CURRENT_FILEEXTENSION", currentFileExtension_tFileList_2);
					globalMap.put("tFileList_2_NB_FILE", NB_FILEtFileList_2);

					log.info("tFileList_2 - Current file or directory path : " + currentFilePath_tFileList_2);

					/**
					 * [tFileList_2 begin ] stop
					 */

					/**
					 * [tFileList_2 main ] start
					 */

					currentComponent = "tFileList_2";

					tos_count_tFileList_2++;

					/**
					 * [tFileList_2 main ] stop
					 */

					/**
					 * [tFileList_2 process_data_begin ] start
					 */

					currentComponent = "tFileList_2";

					/**
					 * [tFileList_2 process_data_begin ] stop
					 */
					NB_ITERATE_tFileProperties_2++;

					if (execStat) {
						runStat.updateStatOnConnection("copyOfout1", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row4", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row6", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row5", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate2", 1, "exec" + NB_ITERATE_tFileProperties_2);
						// Thread.sleep(1000);
					}

					/**
					 * [tLogRow_4 begin ] start
					 */

					ok_Hash.put("tLogRow_4", false);
					start_Hash.put("tLogRow_4", System.currentTimeMillis());

					currentComponent = "tLogRow_4";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row6");

					int tos_count_tLogRow_4 = 0;

					if (log.isDebugEnabled())
						log.debug("tLogRow_4 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tLogRow_4 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tLogRow_4 = new StringBuilder();
								log4jParamters_tLogRow_4.append("Parameters:");
								log4jParamters_tLogRow_4.append("BASIC_MODE" + " = " + "false");
								log4jParamters_tLogRow_4.append(" | ");
								log4jParamters_tLogRow_4.append("TABLE_PRINT" + " = " + "true");
								log4jParamters_tLogRow_4.append(" | ");
								log4jParamters_tLogRow_4.append("VERTICAL" + " = " + "false");
								log4jParamters_tLogRow_4.append(" | ");
								log4jParamters_tLogRow_4.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
								log4jParamters_tLogRow_4.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tLogRow_4 - " + (log4jParamters_tLogRow_4));
							}
						}
						new BytesLimit65535_tLogRow_4().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tLogRow_4", "tLogRow_4", "tLogRow");
						talendJobLogProcess(globalMap);
					}

					///////////////////////

					class Util_tLogRow_4 {

						String[] des_top = { ".", ".", "-", "+" };

						String[] des_head = { "|=", "=|", "-", "+" };

						String[] des_bottom = { "'", "'", "-", "+" };

						String name = "";

						java.util.List<String[]> list = new java.util.ArrayList<String[]>();

						int[] colLengths = new int[3];

						public void addRow(String[] row) {

							for (int i = 0; i < 3; i++) {
								if (row[i] != null) {
									colLengths[i] = Math.max(colLengths[i], row[i].length());
								}
							}
							list.add(row);
						}

						public void setTableName(String name) {

							this.name = name;
						}

						public StringBuilder format() {

							StringBuilder sb = new StringBuilder();

							sb.append(print(des_top));

							int totals = 0;
							for (int i = 0; i < colLengths.length; i++) {
								totals = totals + colLengths[i];
							}

							// name
							sb.append("|");
							int k = 0;
							for (k = 0; k < (totals + 2 - name.length()) / 2; k++) {
								sb.append(' ');
							}
							sb.append(name);
							for (int i = 0; i < totals + 2 - name.length() - k; i++) {
								sb.append(' ');
							}
							sb.append("|\n");

							// head and rows
							sb.append(print(des_head));
							for (int i = 0; i < list.size(); i++) {

								String[] row = list.get(i);

								java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

								StringBuilder sbformat = new StringBuilder();
								sbformat.append("|%1$-");
								sbformat.append(colLengths[0]);
								sbformat.append("s");

								sbformat.append("|%2$-");
								sbformat.append(colLengths[1]);
								sbformat.append("s");

								sbformat.append("|%3$-");
								sbformat.append(colLengths[2]);
								sbformat.append("s");

								sbformat.append("|\n");

								formatter.format(sbformat.toString(), (Object[]) row);

								sb.append(formatter.toString());
								if (i == 0)
									sb.append(print(des_head)); // print the head
							}

							// end
							sb.append(print(des_bottom));
							return sb;
						}

						private StringBuilder print(String[] fillChars) {
							StringBuilder sb = new StringBuilder();
							// first column
							sb.append(fillChars[0]);
							for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							// last column
							for (int i = 0; i < colLengths[2] - fillChars[1].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[1]);
							sb.append("\n");
							return sb;
						}

						public boolean isTableEmpty() {
							if (list.size() > 1)
								return false;
							return true;
						}
					}
					Util_tLogRow_4 util_tLogRow_4 = new Util_tLogRow_4();
					util_tLogRow_4.setTableName("tLogRow_4");
					util_tLogRow_4.addRow(new String[] { "basename", "size", "mtime", });
					StringBuilder strBuffer_tLogRow_4 = null;
					int nb_line_tLogRow_4 = 0;
///////////////////////    			

					/**
					 * [tLogRow_4 begin ] stop
					 */

					/**
					 * [tFileDelete_2 begin ] start
					 */

					ok_Hash.put("tFileDelete_2", false);
					start_Hash.put("tFileDelete_2", System.currentTimeMillis());

					currentComponent = "tFileDelete_2";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row5");

					int tos_count_tFileDelete_2 = 0;

					if (log.isDebugEnabled())
						log.debug("tFileDelete_2 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tFileDelete_2 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tFileDelete_2 = new StringBuilder();
								log4jParamters_tFileDelete_2.append("Parameters:");
								log4jParamters_tFileDelete_2.append("FILENAME" + " = "
										+ "((String)globalMap.get(\"tFileList_2_CURRENT_FILEPATH\"))");
								log4jParamters_tFileDelete_2.append(" | ");
								log4jParamters_tFileDelete_2.append("FAILON" + " = " + "false");
								log4jParamters_tFileDelete_2.append(" | ");
								log4jParamters_tFileDelete_2.append("FOLDER" + " = " + "false");
								log4jParamters_tFileDelete_2.append(" | ");
								log4jParamters_tFileDelete_2.append("FOLDER_FILE" + " = " + "false");
								log4jParamters_tFileDelete_2.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tFileDelete_2 - " + (log4jParamters_tFileDelete_2));
							}
						}
						new BytesLimit65535_tFileDelete_2().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tFileDelete_2", "tFileDelete_2", "tFileDelete");
						talendJobLogProcess(globalMap);
					}

					/**
					 * [tFileDelete_2 begin ] stop
					 */

					/**
					 * [tLogRow_3 begin ] start
					 */

					ok_Hash.put("tLogRow_3", false);
					start_Hash.put("tLogRow_3", System.currentTimeMillis());

					currentComponent = "tLogRow_3";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "copyOfout1");

					int tos_count_tLogRow_3 = 0;

					if (log.isDebugEnabled())
						log.debug("tLogRow_3 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tLogRow_3 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tLogRow_3 = new StringBuilder();
								log4jParamters_tLogRow_3.append("Parameters:");
								log4jParamters_tLogRow_3.append("BASIC_MODE" + " = " + "false");
								log4jParamters_tLogRow_3.append(" | ");
								log4jParamters_tLogRow_3.append("TABLE_PRINT" + " = " + "true");
								log4jParamters_tLogRow_3.append(" | ");
								log4jParamters_tLogRow_3.append("VERTICAL" + " = " + "false");
								log4jParamters_tLogRow_3.append(" | ");
								log4jParamters_tLogRow_3.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
								log4jParamters_tLogRow_3.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tLogRow_3 - " + (log4jParamters_tLogRow_3));
							}
						}
						new BytesLimit65535_tLogRow_3().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tLogRow_3", "tLogRow_3", "tLogRow");
						talendJobLogProcess(globalMap);
					}

					///////////////////////

					class Util_tLogRow_3 {

						String[] des_top = { ".", ".", "-", "+" };

						String[] des_head = { "|=", "=|", "-", "+" };

						String[] des_bottom = { "'", "'", "-", "+" };

						String name = "";

						java.util.List<String[]> list = new java.util.ArrayList<String[]>();

						int[] colLengths = new int[3];

						public void addRow(String[] row) {

							for (int i = 0; i < 3; i++) {
								if (row[i] != null) {
									colLengths[i] = Math.max(colLengths[i], row[i].length());
								}
							}
							list.add(row);
						}

						public void setTableName(String name) {

							this.name = name;
						}

						public StringBuilder format() {

							StringBuilder sb = new StringBuilder();

							sb.append(print(des_top));

							int totals = 0;
							for (int i = 0; i < colLengths.length; i++) {
								totals = totals + colLengths[i];
							}

							// name
							sb.append("|");
							int k = 0;
							for (k = 0; k < (totals + 2 - name.length()) / 2; k++) {
								sb.append(' ');
							}
							sb.append(name);
							for (int i = 0; i < totals + 2 - name.length() - k; i++) {
								sb.append(' ');
							}
							sb.append("|\n");

							// head and rows
							sb.append(print(des_head));
							for (int i = 0; i < list.size(); i++) {

								String[] row = list.get(i);

								java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

								StringBuilder sbformat = new StringBuilder();
								sbformat.append("|%1$-");
								sbformat.append(colLengths[0]);
								sbformat.append("s");

								sbformat.append("|%2$-");
								sbformat.append(colLengths[1]);
								sbformat.append("s");

								sbformat.append("|%3$-");
								sbformat.append(colLengths[2]);
								sbformat.append("s");

								sbformat.append("|\n");

								formatter.format(sbformat.toString(), (Object[]) row);

								sb.append(formatter.toString());
								if (i == 0)
									sb.append(print(des_head)); // print the head
							}

							// end
							sb.append(print(des_bottom));
							return sb;
						}

						private StringBuilder print(String[] fillChars) {
							StringBuilder sb = new StringBuilder();
							// first column
							sb.append(fillChars[0]);
							for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							// last column
							for (int i = 0; i < colLengths[2] - fillChars[1].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[1]);
							sb.append("\n");
							return sb;
						}

						public boolean isTableEmpty() {
							if (list.size() > 1)
								return false;
							return true;
						}
					}
					Util_tLogRow_3 util_tLogRow_3 = new Util_tLogRow_3();
					util_tLogRow_3.setTableName("tLogRow_3");
					util_tLogRow_3.addRow(new String[] { "basename", "size", "mtime", });
					StringBuilder strBuffer_tLogRow_3 = null;
					int nb_line_tLogRow_3 = 0;
///////////////////////    			

					/**
					 * [tLogRow_3 begin ] stop
					 */

					/**
					 * [tMap_2 begin ] start
					 */

					ok_Hash.put("tMap_2", false);
					start_Hash.put("tMap_2", System.currentTimeMillis());

					currentComponent = "tMap_2";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row4");

					int tos_count_tMap_2 = 0;

					if (log.isDebugEnabled())
						log.debug("tMap_2 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tMap_2 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tMap_2 = new StringBuilder();
								log4jParamters_tMap_2.append("Parameters:");
								log4jParamters_tMap_2.append("LINK_STYLE" + " = " + "AUTO");
								log4jParamters_tMap_2.append(" | ");
								log4jParamters_tMap_2.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
								log4jParamters_tMap_2.append(" | ");
								log4jParamters_tMap_2.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
								log4jParamters_tMap_2.append(" | ");
								log4jParamters_tMap_2.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
								log4jParamters_tMap_2.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tMap_2 - " + (log4jParamters_tMap_2));
							}
						}
						new BytesLimit65535_tMap_2().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tMap_2", "tMap_2", "tMap");
						talendJobLogProcess(globalMap);
					}

// ###############################
// # Lookup's keys initialization
					int count_row4_tMap_2 = 0;

// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_2__Struct {
					}
					Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
					int count_copyOfout1_tMap_2 = 0;

					copyOfout1Struct copyOfout1_tmp = new copyOfout1Struct();
// ###############################

					/**
					 * [tMap_2 begin ] stop
					 */

					/**
					 * [tFileProperties_2 begin ] start
					 */

					ok_Hash.put("tFileProperties_2", false);
					start_Hash.put("tFileProperties_2", System.currentTimeMillis());

					currentComponent = "tFileProperties_2";

					int tos_count_tFileProperties_2 = 0;

					if (log.isDebugEnabled())
						log.debug("tFileProperties_2 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tFileProperties_2 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tFileProperties_2 = new StringBuilder();
								log4jParamters_tFileProperties_2.append("Parameters:");
								log4jParamters_tFileProperties_2.append("FILENAME" + " = "
										+ "((String)globalMap.get(\"tFileList_2_CURRENT_FILEPATH\"))");
								log4jParamters_tFileProperties_2.append(" | ");
								log4jParamters_tFileProperties_2.append("MD5" + " = " + "false");
								log4jParamters_tFileProperties_2.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tFileProperties_2 - " + (log4jParamters_tFileProperties_2));
							}
						}
						new BytesLimit65535_tFileProperties_2().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tFileProperties_2", "tFileProperties_2", "tFileProperties");
						talendJobLogProcess(globalMap);
					}

					final StringBuffer log4jSb_tFileProperties_2 = new StringBuffer();

					java.io.File file_tFileProperties_2 = new java.io.File(
							((String) globalMap.get("tFileList_2_CURRENT_FILEPATH")));
					row4 = new row4Struct();

					if (file_tFileProperties_2.exists()) {
						row4.abs_path = file_tFileProperties_2.getAbsolutePath();
						row4.dirname = file_tFileProperties_2.getParent();
						row4.basename = file_tFileProperties_2.getName();
						String r_tFileProperties_2 = (file_tFileProperties_2.canRead()) ? "r" : "-";
						String w_tFileProperties_2 = (file_tFileProperties_2.canWrite()) ? "w" : "-";
						// String x_ = (file_.canExecute())?"x":"-"; /*since JDK1.6*/
						row4.mode_string = r_tFileProperties_2 + w_tFileProperties_2;
						row4.size = file_tFileProperties_2.length();
						row4.mtime = file_tFileProperties_2.lastModified();
						row4.mtime_string = (new java.util.Date(file_tFileProperties_2.lastModified())).toString();

					} else {
						log.info("tFileProperties_2 - File : " + file_tFileProperties_2.getAbsolutePath()
								+ " doesn't exist.");
					}

					/**
					 * [tFileProperties_2 begin ] stop
					 */

					/**
					 * [tFileProperties_2 main ] start
					 */

					currentComponent = "tFileProperties_2";

					tos_count_tFileProperties_2++;

					/**
					 * [tFileProperties_2 main ] stop
					 */

					/**
					 * [tFileProperties_2 process_data_begin ] start
					 */

					currentComponent = "tFileProperties_2";

					/**
					 * [tFileProperties_2 process_data_begin ] stop
					 */

					/**
					 * [tMap_2 main ] start
					 */

					currentComponent = "tMap_2";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row4", "tFileProperties_2", "tFileProperties_2", "tFileProperties", "tMap_2", "tMap_2",
							"tMap"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row4 - " + (row4 == null ? "" : row4.toLogString()));
					}

					boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

					// ###############################
					// # Input tables (lookups)

					boolean rejectedInnerJoin_tMap_2 = false;
					boolean mainRowRejected_tMap_2 = false;

					if (

					(

					(TalendDate.getCurrentDate().getTime() - row4.mtime) / (24 * 60 * 60 * 1000) >= 2

					)

					) { // G_TM_M_280

						// CALL close main tMap filter for table 'row4'
						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
							// ###############################
							// # Output tables

							copyOfout1 = null;

// # Output table : 'copyOfout1'
							count_copyOfout1_tMap_2++;

							copyOfout1_tmp.basename = row4.basename;
							copyOfout1_tmp.size = ((row4.size) / (1000000)) + " mb";
							copyOfout1_tmp.mtime = ((TalendDate.getCurrentDate().getTime() - row4.mtime)
									/ (24 * 60 * 60 * 1000)) + " dias";
							copyOfout1 = copyOfout1_tmp;
							log.debug("tMap_2 - Outputting the record " + count_copyOfout1_tMap_2
									+ " of the output table 'copyOfout1'.");

// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_2 = false;

						tos_count_tMap_2++;

						/**
						 * [tMap_2 main ] stop
						 */

						/**
						 * [tMap_2 process_data_begin ] start
						 */

						currentComponent = "tMap_2";

						/**
						 * [tMap_2 process_data_begin ] stop
						 */
// Start of branch "copyOfout1"
						if (copyOfout1 != null) {

							/**
							 * [tLogRow_3 main ] start
							 */

							currentComponent = "tLogRow_3";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "copyOfout1", "tMap_2", "tMap_2", "tMap", "tLogRow_3", "tLogRow_3", "tLogRow"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("copyOfout1 - " + (copyOfout1 == null ? "" : copyOfout1.toLogString()));
							}

///////////////////////		

							String[] row_tLogRow_3 = new String[3];

							if (copyOfout1.basename != null) { //
								row_tLogRow_3[0] = String.valueOf(copyOfout1.basename);

							} //

							if (copyOfout1.size != null) { //
								row_tLogRow_3[1] = String.valueOf(copyOfout1.size);

							} //

							if (copyOfout1.mtime != null) { //
								row_tLogRow_3[2] = String.valueOf(copyOfout1.mtime);

							} //

							util_tLogRow_3.addRow(row_tLogRow_3);
							nb_line_tLogRow_3++;
							log.info("tLogRow_3 - Content of row " + nb_line_tLogRow_3 + ": "
									+ TalendString.unionString("|", row_tLogRow_3));
//////

//////                    

///////////////////////    			

							row5 = copyOfout1;

							tos_count_tLogRow_3++;

							/**
							 * [tLogRow_3 main ] stop
							 */

							/**
							 * [tLogRow_3 process_data_begin ] start
							 */

							currentComponent = "tLogRow_3";

							/**
							 * [tLogRow_3 process_data_begin ] stop
							 */

							/**
							 * [tFileDelete_2 main ] start
							 */

							currentComponent = "tFileDelete_2";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row5", "tLogRow_3", "tLogRow_3", "tLogRow", "tFileDelete_2", "tFileDelete_2",
									"tFileDelete"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row5 - " + (row5 == null ? "" : row5.toLogString()));
							}

							final StringBuffer log4jSb_tFileDelete_2 = new StringBuffer();

							class DeleteFoldertFileDelete_2 {
								/**
								 * delete all the sub-files in 'file'
								 * 
								 * @param file
								 */
								public boolean delete(java.io.File file) {
									java.io.File[] files = file.listFiles();
									for (int i = 0; i < files.length; i++) {
										if (files[i].isFile()) {
											files[i].delete();
										} else if (files[i].isDirectory()) {
											if (!files[i].delete()) {
												delete(files[i]);
											}
										}
									}
									deleteDirectory(file);
									return file.delete();
								}

								/**
								 * delete all the sub-folders in 'file'
								 * 
								 * @param file
								 */
								private void deleteDirectory(java.io.File file) {
									java.io.File[] filed = file.listFiles();
									for (int i = 0; i < filed.length; i++) {
										if (filed[i].isDirectory()) {
											deleteDirectory(filed[i]);
										}
										filed[i].delete();
									}
								}

							}
							java.io.File file_tFileDelete_2 = new java.io.File(
									((String) globalMap.get("tFileList_2_CURRENT_FILEPATH")));
							if (file_tFileDelete_2.exists() && file_tFileDelete_2.isFile()) {
								if (file_tFileDelete_2.delete()) {
									globalMap.put("tFileDelete_2_CURRENT_STATUS", "File deleted.");
									log.info("tFileDelete_2 - File : " + file_tFileDelete_2.getAbsolutePath()
											+ " is deleted.");
								} else {
									globalMap.put("tFileDelete_2_CURRENT_STATUS", "No file deleted.");
									log.error("tFileDelete_2 - Fail to delete file : "
											+ file_tFileDelete_2.getAbsolutePath());
									globalMap.put("tFileDelete_2_ERROR_MESSAGE",
											"File " + file_tFileDelete_2.getAbsolutePath() + " can not be deleted.");
								}
							} else {
								globalMap.put("tFileDelete_2_CURRENT_STATUS", "File does not exist or is invalid.");
								log.error("tFileDelete_2 - " + file_tFileDelete_2.getAbsolutePath()
										+ " does not exist or is invalid or is not a file.");
								globalMap.put("tFileDelete_2_ERROR_MESSAGE",
										"File " + file_tFileDelete_2.getAbsolutePath()
												+ " does not exist or is invalid or is not a file.");
							}
							globalMap.put("tFileDelete_2_DELETE_PATH",
									((String) globalMap.get("tFileList_2_CURRENT_FILEPATH")));

							row6 = row5;

							tos_count_tFileDelete_2++;

							/**
							 * [tFileDelete_2 main ] stop
							 */

							/**
							 * [tFileDelete_2 process_data_begin ] start
							 */

							currentComponent = "tFileDelete_2";

							/**
							 * [tFileDelete_2 process_data_begin ] stop
							 */

							/**
							 * [tLogRow_4 main ] start
							 */

							currentComponent = "tLogRow_4";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row6", "tFileDelete_2", "tFileDelete_2", "tFileDelete", "tLogRow_4", "tLogRow_4",
									"tLogRow"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row6 - " + (row6 == null ? "" : row6.toLogString()));
							}

///////////////////////		

							String[] row_tLogRow_4 = new String[3];

							if (row6.basename != null) { //
								row_tLogRow_4[0] = String.valueOf(row6.basename);

							} //

							if (row6.size != null) { //
								row_tLogRow_4[1] = String.valueOf(row6.size);

							} //

							if (row6.mtime != null) { //
								row_tLogRow_4[2] = String.valueOf(row6.mtime);

							} //

							util_tLogRow_4.addRow(row_tLogRow_4);
							nb_line_tLogRow_4++;
							log.info("tLogRow_4 - Content of row " + nb_line_tLogRow_4 + ": "
									+ TalendString.unionString("|", row_tLogRow_4));
//////

//////                    

///////////////////////    			

							tos_count_tLogRow_4++;

							/**
							 * [tLogRow_4 main ] stop
							 */

							/**
							 * [tLogRow_4 process_data_begin ] start
							 */

							currentComponent = "tLogRow_4";

							/**
							 * [tLogRow_4 process_data_begin ] stop
							 */

							/**
							 * [tLogRow_4 process_data_end ] start
							 */

							currentComponent = "tLogRow_4";

							/**
							 * [tLogRow_4 process_data_end ] stop
							 */

							/**
							 * [tFileDelete_2 process_data_end ] start
							 */

							currentComponent = "tFileDelete_2";

							/**
							 * [tFileDelete_2 process_data_end ] stop
							 */

							/**
							 * [tLogRow_3 process_data_end ] start
							 */

							currentComponent = "tLogRow_3";

							/**
							 * [tLogRow_3 process_data_end ] stop
							 */

						} // End of branch "copyOfout1"

					} // G_TM_M_280 close main tMap filter for table 'row4'

					/**
					 * [tMap_2 process_data_end ] start
					 */

					currentComponent = "tMap_2";

					/**
					 * [tMap_2 process_data_end ] stop
					 */

					/**
					 * [tFileProperties_2 process_data_end ] start
					 */

					currentComponent = "tFileProperties_2";

					/**
					 * [tFileProperties_2 process_data_end ] stop
					 */

					/**
					 * [tFileProperties_2 end ] start
					 */

					currentComponent = "tFileProperties_2";

					if (log.isDebugEnabled())
						log.debug("tFileProperties_2 - " + ("Done."));

					ok_Hash.put("tFileProperties_2", true);
					end_Hash.put("tFileProperties_2", System.currentTimeMillis());

					/**
					 * [tFileProperties_2 end ] stop
					 */

					/**
					 * [tMap_2 end ] start
					 */

					currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
// ###############################      
					log.debug("tMap_2 - Written records count in the table 'copyOfout1': " + count_copyOfout1_tMap_2
							+ ".");

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row4", 2, 0,
							"tFileProperties_2", "tFileProperties_2", "tFileProperties", "tMap_2", "tMap_2", "tMap",
							"output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tMap_2 - " + ("Done."));

					ok_Hash.put("tMap_2", true);
					end_Hash.put("tMap_2", System.currentTimeMillis());

					/**
					 * [tMap_2 end ] stop
					 */

					/**
					 * [tLogRow_3 end ] start
					 */

					currentComponent = "tLogRow_3";

//////

					java.io.PrintStream consoleOut_tLogRow_3 = null;
					if (globalMap.get("tLogRow_CONSOLE") != null) {
						consoleOut_tLogRow_3 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
					} else {
						consoleOut_tLogRow_3 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
						globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_3);
					}

					consoleOut_tLogRow_3.println(util_tLogRow_3.format().toString());
					consoleOut_tLogRow_3.flush();
//////
					globalMap.put("tLogRow_3_NB_LINE", nb_line_tLogRow_3);
					if (log.isInfoEnabled())
						log.info("tLogRow_3 - " + ("Printed row count: ") + (nb_line_tLogRow_3) + ("."));

///////////////////////    			

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "copyOfout1", 2, 0,
							"tMap_2", "tMap_2", "tMap", "tLogRow_3", "tLogRow_3", "tLogRow", "output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tLogRow_3 - " + ("Done."));

					ok_Hash.put("tLogRow_3", true);
					end_Hash.put("tLogRow_3", System.currentTimeMillis());

					/**
					 * [tLogRow_3 end ] stop
					 */

					/**
					 * [tFileDelete_2 end ] start
					 */

					currentComponent = "tFileDelete_2";

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row5", 2, 0,
							"tLogRow_3", "tLogRow_3", "tLogRow", "tFileDelete_2", "tFileDelete_2", "tFileDelete",
							"output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tFileDelete_2 - " + ("Done."));

					ok_Hash.put("tFileDelete_2", true);
					end_Hash.put("tFileDelete_2", System.currentTimeMillis());

					/**
					 * [tFileDelete_2 end ] stop
					 */

					/**
					 * [tLogRow_4 end ] start
					 */

					currentComponent = "tLogRow_4";

//////

					java.io.PrintStream consoleOut_tLogRow_4 = null;
					if (globalMap.get("tLogRow_CONSOLE") != null) {
						consoleOut_tLogRow_4 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
					} else {
						consoleOut_tLogRow_4 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
						globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_4);
					}

					consoleOut_tLogRow_4.println(util_tLogRow_4.format().toString());
					consoleOut_tLogRow_4.flush();
//////
					globalMap.put("tLogRow_4_NB_LINE", nb_line_tLogRow_4);
					if (log.isInfoEnabled())
						log.info("tLogRow_4 - " + ("Printed row count: ") + (nb_line_tLogRow_4) + ("."));

///////////////////////    			

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row6", 2, 0,
							"tFileDelete_2", "tFileDelete_2", "tFileDelete", "tLogRow_4", "tLogRow_4", "tLogRow",
							"output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tLogRow_4 - " + ("Done."));

					ok_Hash.put("tLogRow_4", true);
					end_Hash.put("tLogRow_4", System.currentTimeMillis());

					/**
					 * [tLogRow_4 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate2", 2, "exec" + NB_ITERATE_tFileProperties_2);
					}

					/**
					 * [tFileList_2 process_data_end ] start
					 */

					currentComponent = "tFileList_2";

					/**
					 * [tFileList_2 process_data_end ] stop
					 */

					/**
					 * [tFileList_2 end ] start
					 */

					currentComponent = "tFileList_2";

				}
				globalMap.put("tFileList_2_NB_FILE", NB_FILEtFileList_2);

				log.info("tFileList_2 - File or directory count : " + NB_FILEtFileList_2);

				if (log.isDebugEnabled())
					log.debug("tFileList_2 - " + ("Done."));

				ok_Hash.put("tFileList_2", true);
				end_Hash.put("tFileList_2", System.currentTimeMillis());

				/**
				 * [tFileList_2 end ] stop
				 */
			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileList_2:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk2", 0, "ok");
			}

			tFileList_3Process(globalMap);

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileList_2 finally ] start
				 */

				currentComponent = "tFileList_2";

				/**
				 * [tFileList_2 finally ] stop
				 */

				/**
				 * [tFileProperties_2 finally ] start
				 */

				currentComponent = "tFileProperties_2";

				/**
				 * [tFileProperties_2 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tLogRow_3 finally ] start
				 */

				currentComponent = "tLogRow_3";

				/**
				 * [tLogRow_3 finally ] stop
				 */

				/**
				 * [tFileDelete_2 finally ] start
				 */

				currentComponent = "tFileDelete_2";

				/**
				 * [tFileDelete_2 finally ] stop
				 */

				/**
				 * [tLogRow_4 finally ] start
				 */

				currentComponent = "tLogRow_4";

				/**
				 * [tLogRow_4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileList_2_SUBPROCESS_STATE", 1);
	}

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return null;

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String size;

		public String getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 255;
		}

		public Integer sizePrecision() {
			return 2;
		}

		public String sizeDefault() {

			return null;

		}

		public String sizeComment() {

			return "";

		}

		public String sizePattern() {

			return "";

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public String mtime;

		public String getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return null;

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return "dd-MM-yyyy";

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("basename=" + basename);
			sb.append(",size=" + size);
			sb.append(",mtime=" + mtime);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return null;

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String size;

		public String getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 255;
		}

		public Integer sizePrecision() {
			return 2;
		}

		public String sizeDefault() {

			return null;

		}

		public String sizeComment() {

			return "";

		}

		public String sizePattern() {

			return "";

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public String mtime;

		public String getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return null;

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return "dd-MM-yyyy";

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("basename=" + basename);
			sb.append(",size=" + size);
			sb.append(",mtime=" + mtime);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class copyOfout1_0Struct implements routines.system.IPersistableRow<copyOfout1_0Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return null;

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String size;

		public String getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 255;
		}

		public Integer sizePrecision() {
			return 2;
		}

		public String sizeDefault() {

			return null;

		}

		public String sizeComment() {

			return "";

		}

		public String sizePattern() {

			return "";

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public String mtime;

		public String getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return null;

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return "dd-MM-yyyy";

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.basename = readString(dis);

					this.size = readString(dis);

					this.mtime = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.size, dos);

				// String

				writeString(this.mtime, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("basename=" + basename);
			sb.append(",size=" + size);
			sb.append(",mtime=" + mtime);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(copyOfout1_0Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_TESTE_JP_Cleaning_server = new byte[0];
		static byte[] commonByteArray_TESTE_JP_Cleaning_server = new byte[0];

		public String abs_path;

		public String getAbs_path() {
			return this.abs_path;
		}

		public Boolean abs_pathIsNullable() {
			return true;
		}

		public Boolean abs_pathIsKey() {
			return false;
		}

		public Integer abs_pathLength() {
			return 255;
		}

		public Integer abs_pathPrecision() {
			return 0;
		}

		public String abs_pathDefault() {

			return "";

		}

		public String abs_pathComment() {

			return null;

		}

		public String abs_pathPattern() {

			return null;

		}

		public String abs_pathOriginalDbColumnName() {

			return "abs_path";

		}

		public String dirname;

		public String getDirname() {
			return this.dirname;
		}

		public Boolean dirnameIsNullable() {
			return true;
		}

		public Boolean dirnameIsKey() {
			return false;
		}

		public Integer dirnameLength() {
			return 255;
		}

		public Integer dirnamePrecision() {
			return 0;
		}

		public String dirnameDefault() {

			return "";

		}

		public String dirnameComment() {

			return null;

		}

		public String dirnamePattern() {

			return null;

		}

		public String dirnameOriginalDbColumnName() {

			return "dirname";

		}

		public String basename;

		public String getBasename() {
			return this.basename;
		}

		public Boolean basenameIsNullable() {
			return true;
		}

		public Boolean basenameIsKey() {
			return false;
		}

		public Integer basenameLength() {
			return 255;
		}

		public Integer basenamePrecision() {
			return 0;
		}

		public String basenameDefault() {

			return "";

		}

		public String basenameComment() {

			return null;

		}

		public String basenamePattern() {

			return null;

		}

		public String basenameOriginalDbColumnName() {

			return "basename";

		}

		public String mode_string;

		public String getMode_string() {
			return this.mode_string;
		}

		public Boolean mode_stringIsNullable() {
			return true;
		}

		public Boolean mode_stringIsKey() {
			return false;
		}

		public Integer mode_stringLength() {
			return 10;
		}

		public Integer mode_stringPrecision() {
			return 0;
		}

		public String mode_stringDefault() {

			return "";

		}

		public String mode_stringComment() {

			return null;

		}

		public String mode_stringPattern() {

			return null;

		}

		public String mode_stringOriginalDbColumnName() {

			return "mode_string";

		}

		public Long size;

		public Long getSize() {
			return this.size;
		}

		public Boolean sizeIsNullable() {
			return true;
		}

		public Boolean sizeIsKey() {
			return false;
		}

		public Integer sizeLength() {
			return 20;
		}

		public Integer sizePrecision() {
			return 0;
		}

		public String sizeDefault() {

			return "";

		}

		public String sizeComment() {

			return null;

		}

		public String sizePattern() {

			return null;

		}

		public String sizeOriginalDbColumnName() {

			return "size";

		}

		public Long mtime;

		public Long getMtime() {
			return this.mtime;
		}

		public Boolean mtimeIsNullable() {
			return true;
		}

		public Boolean mtimeIsKey() {
			return false;
		}

		public Integer mtimeLength() {
			return 20;
		}

		public Integer mtimePrecision() {
			return 0;
		}

		public String mtimeDefault() {

			return "";

		}

		public String mtimeComment() {

			return null;

		}

		public String mtimePattern() {

			return null;

		}

		public String mtimeOriginalDbColumnName() {

			return "mtime";

		}

		public String mtime_string;

		public String getMtime_string() {
			return this.mtime_string;
		}

		public Boolean mtime_stringIsNullable() {
			return true;
		}

		public Boolean mtime_stringIsKey() {
			return false;
		}

		public Integer mtime_stringLength() {
			return 20;
		}

		public Integer mtime_stringPrecision() {
			return 0;
		}

		public String mtime_stringDefault() {

			return "";

		}

		public String mtime_stringComment() {

			return null;

		}

		public String mtime_stringPattern() {

			return null;

		}

		public String mtime_stringOriginalDbColumnName() {

			return "mtime_string";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TESTE_JP_Cleaning_server.length) {
					if (length < 1024 && commonByteArray_TESTE_JP_Cleaning_server.length == 0) {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[1024];
					} else {
						commonByteArray_TESTE_JP_Cleaning_server = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TESTE_JP_Cleaning_server, 0, length);
				strReturn = new String(commonByteArray_TESTE_JP_Cleaning_server, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.abs_path = readString(dis);

					this.dirname = readString(dis);

					this.basename = readString(dis);

					this.mode_string = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.size = null;
					} else {
						this.size = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.mtime = null;
					} else {
						this.mtime = dis.readLong();
					}

					this.mtime_string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TESTE_JP_Cleaning_server) {

				try {

					int length = 0;

					this.abs_path = readString(dis);

					this.dirname = readString(dis);

					this.basename = readString(dis);

					this.mode_string = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.size = null;
					} else {
						this.size = dis.readLong();
					}

					length = dis.readByte();
					if (length == -1) {
						this.mtime = null;
					} else {
						this.mtime = dis.readLong();
					}

					this.mtime_string = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.abs_path, dos);

				// String

				writeString(this.dirname, dos);

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.mode_string, dos);

				// Long

				if (this.size == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.size);
				}

				// Long

				if (this.mtime == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.mtime);
				}

				// String

				writeString(this.mtime_string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.abs_path, dos);

				// String

				writeString(this.dirname, dos);

				// String

				writeString(this.basename, dos);

				// String

				writeString(this.mode_string, dos);

				// Long

				if (this.size == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.size);
				}

				// Long

				if (this.mtime == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeLong(this.mtime);
				}

				// String

				writeString(this.mtime_string, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("abs_path=" + abs_path);
			sb.append(",dirname=" + dirname);
			sb.append(",basename=" + basename);
			sb.append(",mode_string=" + mode_string);
			sb.append(",size=" + String.valueOf(size));
			sb.append(",mtime=" + String.valueOf(mtime));
			sb.append(",mtime_string=" + mtime_string);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (abs_path == null) {
				sb.append("<null>");
			} else {
				sb.append(abs_path);
			}

			sb.append("|");

			if (dirname == null) {
				sb.append("<null>");
			} else {
				sb.append(dirname);
			}

			sb.append("|");

			if (basename == null) {
				sb.append("<null>");
			} else {
				sb.append(basename);
			}

			sb.append("|");

			if (mode_string == null) {
				sb.append("<null>");
			} else {
				sb.append(mode_string);
			}

			sb.append("|");

			if (size == null) {
				sb.append("<null>");
			} else {
				sb.append(size);
			}

			sb.append("|");

			if (mtime == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime);
			}

			sb.append("|");

			if (mtime_string == null) {
				sb.append("<null>");
			} else {
				sb.append(mtime_string);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileList_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileList_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "tFileList_3");
		org.slf4j.MDC.put("_subJobPid", TalendString.getAsciiRandomString(6));

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row7Struct row7 = new row7Struct();
				copyOfout1_0Struct copyOfout1_0 = new copyOfout1_0Struct();
				copyOfout1_0Struct row8 = copyOfout1_0;
				copyOfout1_0Struct row9 = copyOfout1_0;

				/**
				 * [tFileList_3 begin ] start
				 */

				int NB_ITERATE_tFileProperties_3 = 0; // for statistics

				ok_Hash.put("tFileList_3", false);
				start_Hash.put("tFileList_3", System.currentTimeMillis());

				currentComponent = "tFileList_3";

				int tos_count_tFileList_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileList_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileList_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileList_3 = new StringBuilder();
							log4jParamters_tFileList_3.append("Parameters:");
							log4jParamters_tFileList_3.append("DIRECTORY" + " = " + "context.folder_directory2");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("LIST_MODE" + " = " + "FILES");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("INCLUDSUBDIR" + " = " + "false");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("CASE_SENSITIVE" + " = " + "YES");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("ERROR" + " = " + "false");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("GLOBEXPRESSIONS" + " = " + "true");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3
									.append("FILES" + " = " + "[{FILEMASK=" + ("context.file_mask2") + "}]");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("ORDER_BY_NOTHING" + " = " + "true");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("ORDER_BY_FILENAME" + " = " + "false");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("ORDER_BY_FILESIZE" + " = " + "false");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("ORDER_BY_MODIFIEDDATE" + " = " + "false");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("ORDER_ACTION_ASC" + " = " + "true");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("ORDER_ACTION_DESC" + " = " + "false");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("IFEXCLUDE" + " = " + "false");
							log4jParamters_tFileList_3.append(" | ");
							log4jParamters_tFileList_3.append("FORMAT_FILEPATH_TO_SLASH" + " = " + "false");
							log4jParamters_tFileList_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileList_3 - " + (log4jParamters_tFileList_3));
						}
					}
					new BytesLimit65535_tFileList_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileList_3", "tFileList_3", "tFileList");
					talendJobLogProcess(globalMap);
				}

				final StringBuffer log4jSb_tFileList_3 = new StringBuffer();

				String directory_tFileList_3 = context.folder_directory2;
				final java.util.List<String> maskList_tFileList_3 = new java.util.ArrayList<String>();
				final java.util.List<java.util.regex.Pattern> patternList_tFileList_3 = new java.util.ArrayList<java.util.regex.Pattern>();
				maskList_tFileList_3.add(context.file_mask2);
				for (final String filemask_tFileList_3 : maskList_tFileList_3) {
					String filemask_compile_tFileList_3 = filemask_tFileList_3;

					filemask_compile_tFileList_3 = org.apache.oro.text.GlobCompiler.globToPerl5(
							filemask_tFileList_3.toCharArray(), org.apache.oro.text.GlobCompiler.DEFAULT_MASK);

					java.util.regex.Pattern fileNamePattern_tFileList_3 = java.util.regex.Pattern
							.compile(filemask_compile_tFileList_3);
					patternList_tFileList_3.add(fileNamePattern_tFileList_3);
				}
				int NB_FILEtFileList_3 = 0;

				final boolean case_sensitive_tFileList_3 = true;

				log.info("tFileList_3 - Starting to search for matching entries.");

				final java.util.List<java.io.File> list_tFileList_3 = new java.util.ArrayList<java.io.File>();
				final java.util.Set<String> filePath_tFileList_3 = new java.util.HashSet<String>();
				java.io.File file_tFileList_3 = new java.io.File(directory_tFileList_3);

				file_tFileList_3.listFiles(new java.io.FilenameFilter() {
					public boolean accept(java.io.File dir, String name) {
						java.io.File file = new java.io.File(dir, name);
						if (!file.isDirectory()) {

							String fileName_tFileList_3 = file.getName();
							for (final java.util.regex.Pattern fileNamePattern_tFileList_3 : patternList_tFileList_3) {
								if (fileNamePattern_tFileList_3.matcher(fileName_tFileList_3).matches()) {
									if (!filePath_tFileList_3.contains(file.getAbsolutePath())) {
										list_tFileList_3.add(file);
										filePath_tFileList_3.add(file.getAbsolutePath());
									}
								}
							}
						}
						return true;
					}
				});
				java.util.Collections.sort(list_tFileList_3);

				log.info("tFileList_3 - Start to list files.");

				for (int i_tFileList_3 = 0; i_tFileList_3 < list_tFileList_3.size(); i_tFileList_3++) {
					java.io.File files_tFileList_3 = list_tFileList_3.get(i_tFileList_3);
					String fileName_tFileList_3 = files_tFileList_3.getName();

					String currentFileName_tFileList_3 = files_tFileList_3.getName();
					String currentFilePath_tFileList_3 = files_tFileList_3.getAbsolutePath();
					String currentFileDirectory_tFileList_3 = files_tFileList_3.getParent();
					String currentFileExtension_tFileList_3 = null;

					if (files_tFileList_3.getName().contains(".") && files_tFileList_3.isFile()) {
						currentFileExtension_tFileList_3 = files_tFileList_3.getName()
								.substring(files_tFileList_3.getName().lastIndexOf(".") + 1);
					} else {
						currentFileExtension_tFileList_3 = "";
					}

					NB_FILEtFileList_3++;
					globalMap.put("tFileList_3_CURRENT_FILE", currentFileName_tFileList_3);
					globalMap.put("tFileList_3_CURRENT_FILEPATH", currentFilePath_tFileList_3);
					globalMap.put("tFileList_3_CURRENT_FILEDIRECTORY", currentFileDirectory_tFileList_3);
					globalMap.put("tFileList_3_CURRENT_FILEEXTENSION", currentFileExtension_tFileList_3);
					globalMap.put("tFileList_3_NB_FILE", NB_FILEtFileList_3);

					log.info("tFileList_3 - Current file or directory path : " + currentFilePath_tFileList_3);

					/**
					 * [tFileList_3 begin ] stop
					 */

					/**
					 * [tFileList_3 main ] start
					 */

					currentComponent = "tFileList_3";

					tos_count_tFileList_3++;

					/**
					 * [tFileList_3 main ] stop
					 */

					/**
					 * [tFileList_3 process_data_begin ] start
					 */

					currentComponent = "tFileList_3";

					/**
					 * [tFileList_3 process_data_begin ] stop
					 */
					NB_ITERATE_tFileProperties_3++;

					if (execStat) {
						runStat.updateStatOnConnection("copyOfout1_0", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row7", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row9", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("row8", 3, 0);
					}

					if (execStat) {
						runStat.updateStatOnConnection("iterate4", 1, "exec" + NB_ITERATE_tFileProperties_3);
						// Thread.sleep(1000);
					}

					/**
					 * [tLogRow_6 begin ] start
					 */

					ok_Hash.put("tLogRow_6", false);
					start_Hash.put("tLogRow_6", System.currentTimeMillis());

					currentComponent = "tLogRow_6";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row9");

					int tos_count_tLogRow_6 = 0;

					if (log.isDebugEnabled())
						log.debug("tLogRow_6 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tLogRow_6 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tLogRow_6 = new StringBuilder();
								log4jParamters_tLogRow_6.append("Parameters:");
								log4jParamters_tLogRow_6.append("BASIC_MODE" + " = " + "false");
								log4jParamters_tLogRow_6.append(" | ");
								log4jParamters_tLogRow_6.append("TABLE_PRINT" + " = " + "true");
								log4jParamters_tLogRow_6.append(" | ");
								log4jParamters_tLogRow_6.append("VERTICAL" + " = " + "false");
								log4jParamters_tLogRow_6.append(" | ");
								log4jParamters_tLogRow_6.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
								log4jParamters_tLogRow_6.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tLogRow_6 - " + (log4jParamters_tLogRow_6));
							}
						}
						new BytesLimit65535_tLogRow_6().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tLogRow_6", "tLogRow_6", "tLogRow");
						talendJobLogProcess(globalMap);
					}

					///////////////////////

					class Util_tLogRow_6 {

						String[] des_top = { ".", ".", "-", "+" };

						String[] des_head = { "|=", "=|", "-", "+" };

						String[] des_bottom = { "'", "'", "-", "+" };

						String name = "";

						java.util.List<String[]> list = new java.util.ArrayList<String[]>();

						int[] colLengths = new int[3];

						public void addRow(String[] row) {

							for (int i = 0; i < 3; i++) {
								if (row[i] != null) {
									colLengths[i] = Math.max(colLengths[i], row[i].length());
								}
							}
							list.add(row);
						}

						public void setTableName(String name) {

							this.name = name;
						}

						public StringBuilder format() {

							StringBuilder sb = new StringBuilder();

							sb.append(print(des_top));

							int totals = 0;
							for (int i = 0; i < colLengths.length; i++) {
								totals = totals + colLengths[i];
							}

							// name
							sb.append("|");
							int k = 0;
							for (k = 0; k < (totals + 2 - name.length()) / 2; k++) {
								sb.append(' ');
							}
							sb.append(name);
							for (int i = 0; i < totals + 2 - name.length() - k; i++) {
								sb.append(' ');
							}
							sb.append("|\n");

							// head and rows
							sb.append(print(des_head));
							for (int i = 0; i < list.size(); i++) {

								String[] row = list.get(i);

								java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

								StringBuilder sbformat = new StringBuilder();
								sbformat.append("|%1$-");
								sbformat.append(colLengths[0]);
								sbformat.append("s");

								sbformat.append("|%2$-");
								sbformat.append(colLengths[1]);
								sbformat.append("s");

								sbformat.append("|%3$-");
								sbformat.append(colLengths[2]);
								sbformat.append("s");

								sbformat.append("|\n");

								formatter.format(sbformat.toString(), (Object[]) row);

								sb.append(formatter.toString());
								if (i == 0)
									sb.append(print(des_head)); // print the head
							}

							// end
							sb.append(print(des_bottom));
							return sb;
						}

						private StringBuilder print(String[] fillChars) {
							StringBuilder sb = new StringBuilder();
							// first column
							sb.append(fillChars[0]);
							for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							// last column
							for (int i = 0; i < colLengths[2] - fillChars[1].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[1]);
							sb.append("\n");
							return sb;
						}

						public boolean isTableEmpty() {
							if (list.size() > 1)
								return false;
							return true;
						}
					}
					Util_tLogRow_6 util_tLogRow_6 = new Util_tLogRow_6();
					util_tLogRow_6.setTableName("tLogRow_6");
					util_tLogRow_6.addRow(new String[] { "basename", "size", "mtime", });
					StringBuilder strBuffer_tLogRow_6 = null;
					int nb_line_tLogRow_6 = 0;
///////////////////////    			

					/**
					 * [tLogRow_6 begin ] stop
					 */

					/**
					 * [tFileDelete_3 begin ] start
					 */

					ok_Hash.put("tFileDelete_3", false);
					start_Hash.put("tFileDelete_3", System.currentTimeMillis());

					currentComponent = "tFileDelete_3";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row8");

					int tos_count_tFileDelete_3 = 0;

					if (log.isDebugEnabled())
						log.debug("tFileDelete_3 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tFileDelete_3 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tFileDelete_3 = new StringBuilder();
								log4jParamters_tFileDelete_3.append("Parameters:");
								log4jParamters_tFileDelete_3.append("FILENAME" + " = "
										+ "((String)globalMap.get(\"tFileList_3_CURRENT_FILEPATH\"))");
								log4jParamters_tFileDelete_3.append(" | ");
								log4jParamters_tFileDelete_3.append("FAILON" + " = " + "false");
								log4jParamters_tFileDelete_3.append(" | ");
								log4jParamters_tFileDelete_3.append("FOLDER" + " = " + "false");
								log4jParamters_tFileDelete_3.append(" | ");
								log4jParamters_tFileDelete_3.append("FOLDER_FILE" + " = " + "false");
								log4jParamters_tFileDelete_3.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tFileDelete_3 - " + (log4jParamters_tFileDelete_3));
							}
						}
						new BytesLimit65535_tFileDelete_3().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tFileDelete_3", "tFileDelete_3", "tFileDelete");
						talendJobLogProcess(globalMap);
					}

					/**
					 * [tFileDelete_3 begin ] stop
					 */

					/**
					 * [tLogRow_5 begin ] start
					 */

					ok_Hash.put("tLogRow_5", false);
					start_Hash.put("tLogRow_5", System.currentTimeMillis());

					currentComponent = "tLogRow_5";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "copyOfout1_0");

					int tos_count_tLogRow_5 = 0;

					if (log.isDebugEnabled())
						log.debug("tLogRow_5 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tLogRow_5 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tLogRow_5 = new StringBuilder();
								log4jParamters_tLogRow_5.append("Parameters:");
								log4jParamters_tLogRow_5.append("BASIC_MODE" + " = " + "false");
								log4jParamters_tLogRow_5.append(" | ");
								log4jParamters_tLogRow_5.append("TABLE_PRINT" + " = " + "true");
								log4jParamters_tLogRow_5.append(" | ");
								log4jParamters_tLogRow_5.append("VERTICAL" + " = " + "false");
								log4jParamters_tLogRow_5.append(" | ");
								log4jParamters_tLogRow_5.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
								log4jParamters_tLogRow_5.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tLogRow_5 - " + (log4jParamters_tLogRow_5));
							}
						}
						new BytesLimit65535_tLogRow_5().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tLogRow_5", "tLogRow_5", "tLogRow");
						talendJobLogProcess(globalMap);
					}

					///////////////////////

					class Util_tLogRow_5 {

						String[] des_top = { ".", ".", "-", "+" };

						String[] des_head = { "|=", "=|", "-", "+" };

						String[] des_bottom = { "'", "'", "-", "+" };

						String name = "";

						java.util.List<String[]> list = new java.util.ArrayList<String[]>();

						int[] colLengths = new int[3];

						public void addRow(String[] row) {

							for (int i = 0; i < 3; i++) {
								if (row[i] != null) {
									colLengths[i] = Math.max(colLengths[i], row[i].length());
								}
							}
							list.add(row);
						}

						public void setTableName(String name) {

							this.name = name;
						}

						public StringBuilder format() {

							StringBuilder sb = new StringBuilder();

							sb.append(print(des_top));

							int totals = 0;
							for (int i = 0; i < colLengths.length; i++) {
								totals = totals + colLengths[i];
							}

							// name
							sb.append("|");
							int k = 0;
							for (k = 0; k < (totals + 2 - name.length()) / 2; k++) {
								sb.append(' ');
							}
							sb.append(name);
							for (int i = 0; i < totals + 2 - name.length() - k; i++) {
								sb.append(' ');
							}
							sb.append("|\n");

							// head and rows
							sb.append(print(des_head));
							for (int i = 0; i < list.size(); i++) {

								String[] row = list.get(i);

								java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

								StringBuilder sbformat = new StringBuilder();
								sbformat.append("|%1$-");
								sbformat.append(colLengths[0]);
								sbformat.append("s");

								sbformat.append("|%2$-");
								sbformat.append(colLengths[1]);
								sbformat.append("s");

								sbformat.append("|%3$-");
								sbformat.append(colLengths[2]);
								sbformat.append("s");

								sbformat.append("|\n");

								formatter.format(sbformat.toString(), (Object[]) row);

								sb.append(formatter.toString());
								if (i == 0)
									sb.append(print(des_head)); // print the head
							}

							// end
							sb.append(print(des_bottom));
							return sb;
						}

						private StringBuilder print(String[] fillChars) {
							StringBuilder sb = new StringBuilder();
							// first column
							sb.append(fillChars[0]);
							for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[3]);

							// last column
							for (int i = 0; i < colLengths[2] - fillChars[1].length() + 1; i++) {
								sb.append(fillChars[2]);
							}
							sb.append(fillChars[1]);
							sb.append("\n");
							return sb;
						}

						public boolean isTableEmpty() {
							if (list.size() > 1)
								return false;
							return true;
						}
					}
					Util_tLogRow_5 util_tLogRow_5 = new Util_tLogRow_5();
					util_tLogRow_5.setTableName("tLogRow_5");
					util_tLogRow_5.addRow(new String[] { "basename", "size", "mtime", });
					StringBuilder strBuffer_tLogRow_5 = null;
					int nb_line_tLogRow_5 = 0;
///////////////////////    			

					/**
					 * [tLogRow_5 begin ] stop
					 */

					/**
					 * [tMap_3 begin ] start
					 */

					ok_Hash.put("tMap_3", false);
					start_Hash.put("tMap_3", System.currentTimeMillis());

					currentComponent = "tMap_3";

					runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row7");

					int tos_count_tMap_3 = 0;

					if (log.isDebugEnabled())
						log.debug("tMap_3 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tMap_3 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tMap_3 = new StringBuilder();
								log4jParamters_tMap_3.append("Parameters:");
								log4jParamters_tMap_3.append("LINK_STYLE" + " = " + "AUTO");
								log4jParamters_tMap_3.append(" | ");
								log4jParamters_tMap_3.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
								log4jParamters_tMap_3.append(" | ");
								log4jParamters_tMap_3.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
								log4jParamters_tMap_3.append(" | ");
								log4jParamters_tMap_3.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
								log4jParamters_tMap_3.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tMap_3 - " + (log4jParamters_tMap_3));
							}
						}
						new BytesLimit65535_tMap_3().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tMap_3", "tMap_3", "tMap");
						talendJobLogProcess(globalMap);
					}

// ###############################
// # Lookup's keys initialization
					int count_row7_tMap_3 = 0;

// ###############################        

// ###############################
// # Vars initialization
					class Var__tMap_3__Struct {
					}
					Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
					int count_copyOfout1_0_tMap_3 = 0;

					copyOfout1_0Struct copyOfout1_0_tmp = new copyOfout1_0Struct();
// ###############################

					/**
					 * [tMap_3 begin ] stop
					 */

					/**
					 * [tFileProperties_3 begin ] start
					 */

					ok_Hash.put("tFileProperties_3", false);
					start_Hash.put("tFileProperties_3", System.currentTimeMillis());

					currentComponent = "tFileProperties_3";

					int tos_count_tFileProperties_3 = 0;

					if (log.isDebugEnabled())
						log.debug("tFileProperties_3 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tFileProperties_3 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tFileProperties_3 = new StringBuilder();
								log4jParamters_tFileProperties_3.append("Parameters:");
								log4jParamters_tFileProperties_3.append("FILENAME" + " = "
										+ "((String)globalMap.get(\"tFileList_3_CURRENT_FILEPATH\"))");
								log4jParamters_tFileProperties_3.append(" | ");
								log4jParamters_tFileProperties_3.append("MD5" + " = " + "false");
								log4jParamters_tFileProperties_3.append(" | ");
								if (log.isDebugEnabled())
									log.debug("tFileProperties_3 - " + (log4jParamters_tFileProperties_3));
							}
						}
						new BytesLimit65535_tFileProperties_3().limitLog4jByte();
					}
					if (enableLogStash) {
						talendJobLog.addCM("tFileProperties_3", "tFileProperties_3", "tFileProperties");
						talendJobLogProcess(globalMap);
					}

					final StringBuffer log4jSb_tFileProperties_3 = new StringBuffer();

					java.io.File file_tFileProperties_3 = new java.io.File(
							((String) globalMap.get("tFileList_3_CURRENT_FILEPATH")));
					row7 = new row7Struct();

					if (file_tFileProperties_3.exists()) {
						row7.abs_path = file_tFileProperties_3.getAbsolutePath();
						row7.dirname = file_tFileProperties_3.getParent();
						row7.basename = file_tFileProperties_3.getName();
						String r_tFileProperties_3 = (file_tFileProperties_3.canRead()) ? "r" : "-";
						String w_tFileProperties_3 = (file_tFileProperties_3.canWrite()) ? "w" : "-";
						// String x_ = (file_.canExecute())?"x":"-"; /*since JDK1.6*/
						row7.mode_string = r_tFileProperties_3 + w_tFileProperties_3;
						row7.size = file_tFileProperties_3.length();
						row7.mtime = file_tFileProperties_3.lastModified();
						row7.mtime_string = (new java.util.Date(file_tFileProperties_3.lastModified())).toString();

					} else {
						log.info("tFileProperties_3 - File : " + file_tFileProperties_3.getAbsolutePath()
								+ " doesn't exist.");
					}

					/**
					 * [tFileProperties_3 begin ] stop
					 */

					/**
					 * [tFileProperties_3 main ] start
					 */

					currentComponent = "tFileProperties_3";

					tos_count_tFileProperties_3++;

					/**
					 * [tFileProperties_3 main ] stop
					 */

					/**
					 * [tFileProperties_3 process_data_begin ] start
					 */

					currentComponent = "tFileProperties_3";

					/**
					 * [tFileProperties_3 process_data_begin ] stop
					 */

					/**
					 * [tMap_3 main ] start
					 */

					currentComponent = "tMap_3";

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row7", "tFileProperties_3", "tFileProperties_3", "tFileProperties", "tMap_3", "tMap_3",
							"tMap"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row7 - " + (row7 == null ? "" : row7.toLogString()));
					}

					boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

					// ###############################
					// # Input tables (lookups)

					boolean rejectedInnerJoin_tMap_3 = false;
					boolean mainRowRejected_tMap_3 = false;

					if (

					(

					(TalendDate.getCurrentDate().getTime() - row7.mtime) / (24 * 60 * 60 * 1000) >= 2

					)

					) { // G_TM_M_280

						// CALL close main tMap filter for table 'row7'
						// ###############################
						{ // start of Var scope

							// ###############################
							// # Vars tables

							Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
							// ###############################
							// # Output tables

							copyOfout1_0 = null;

// # Output table : 'copyOfout1_0'
							count_copyOfout1_0_tMap_3++;

							copyOfout1_0_tmp.basename = row7.basename;
							copyOfout1_0_tmp.size = ((row7.size) / (1000000)) + " mb";
							copyOfout1_0_tmp.mtime = ((TalendDate.getCurrentDate().getTime() - row7.mtime)
									/ (24 * 60 * 60 * 1000)) + " dias";
							copyOfout1_0 = copyOfout1_0_tmp;
							log.debug("tMap_3 - Outputting the record " + count_copyOfout1_0_tMap_3
									+ " of the output table 'copyOfout1_0'.");

// ###############################

						} // end of Var scope

						rejectedInnerJoin_tMap_3 = false;

						tos_count_tMap_3++;

						/**
						 * [tMap_3 main ] stop
						 */

						/**
						 * [tMap_3 process_data_begin ] start
						 */

						currentComponent = "tMap_3";

						/**
						 * [tMap_3 process_data_begin ] stop
						 */
// Start of branch "copyOfout1_0"
						if (copyOfout1_0 != null) {

							/**
							 * [tLogRow_5 main ] start
							 */

							currentComponent = "tLogRow_5";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "copyOfout1_0", "tMap_3", "tMap_3", "tMap", "tLogRow_5", "tLogRow_5", "tLogRow"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("copyOfout1_0 - " + (copyOfout1_0 == null ? "" : copyOfout1_0.toLogString()));
							}

///////////////////////		

							String[] row_tLogRow_5 = new String[3];

							if (copyOfout1_0.basename != null) { //
								row_tLogRow_5[0] = String.valueOf(copyOfout1_0.basename);

							} //

							if (copyOfout1_0.size != null) { //
								row_tLogRow_5[1] = String.valueOf(copyOfout1_0.size);

							} //

							if (copyOfout1_0.mtime != null) { //
								row_tLogRow_5[2] = String.valueOf(copyOfout1_0.mtime);

							} //

							util_tLogRow_5.addRow(row_tLogRow_5);
							nb_line_tLogRow_5++;
							log.info("tLogRow_5 - Content of row " + nb_line_tLogRow_5 + ": "
									+ TalendString.unionString("|", row_tLogRow_5));
//////

//////                    

///////////////////////    			

							row8 = copyOfout1_0;

							tos_count_tLogRow_5++;

							/**
							 * [tLogRow_5 main ] stop
							 */

							/**
							 * [tLogRow_5 process_data_begin ] start
							 */

							currentComponent = "tLogRow_5";

							/**
							 * [tLogRow_5 process_data_begin ] stop
							 */

							/**
							 * [tFileDelete_3 main ] start
							 */

							currentComponent = "tFileDelete_3";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row8", "tLogRow_5", "tLogRow_5", "tLogRow", "tFileDelete_3", "tFileDelete_3",
									"tFileDelete"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
							}

							final StringBuffer log4jSb_tFileDelete_3 = new StringBuffer();

							class DeleteFoldertFileDelete_3 {
								/**
								 * delete all the sub-files in 'file'
								 * 
								 * @param file
								 */
								public boolean delete(java.io.File file) {
									java.io.File[] files = file.listFiles();
									for (int i = 0; i < files.length; i++) {
										if (files[i].isFile()) {
											files[i].delete();
										} else if (files[i].isDirectory()) {
											if (!files[i].delete()) {
												delete(files[i]);
											}
										}
									}
									deleteDirectory(file);
									return file.delete();
								}

								/**
								 * delete all the sub-folders in 'file'
								 * 
								 * @param file
								 */
								private void deleteDirectory(java.io.File file) {
									java.io.File[] filed = file.listFiles();
									for (int i = 0; i < filed.length; i++) {
										if (filed[i].isDirectory()) {
											deleteDirectory(filed[i]);
										}
										filed[i].delete();
									}
								}

							}
							java.io.File file_tFileDelete_3 = new java.io.File(
									((String) globalMap.get("tFileList_3_CURRENT_FILEPATH")));
							if (file_tFileDelete_3.exists() && file_tFileDelete_3.isFile()) {
								if (file_tFileDelete_3.delete()) {
									globalMap.put("tFileDelete_3_CURRENT_STATUS", "File deleted.");
									log.info("tFileDelete_3 - File : " + file_tFileDelete_3.getAbsolutePath()
											+ " is deleted.");
								} else {
									globalMap.put("tFileDelete_3_CURRENT_STATUS", "No file deleted.");
									log.error("tFileDelete_3 - Fail to delete file : "
											+ file_tFileDelete_3.getAbsolutePath());
									globalMap.put("tFileDelete_3_ERROR_MESSAGE",
											"File " + file_tFileDelete_3.getAbsolutePath() + " can not be deleted.");
								}
							} else {
								globalMap.put("tFileDelete_3_CURRENT_STATUS", "File does not exist or is invalid.");
								log.error("tFileDelete_3 - " + file_tFileDelete_3.getAbsolutePath()
										+ " does not exist or is invalid or is not a file.");
								globalMap.put("tFileDelete_3_ERROR_MESSAGE",
										"File " + file_tFileDelete_3.getAbsolutePath()
												+ " does not exist or is invalid or is not a file.");
							}
							globalMap.put("tFileDelete_3_DELETE_PATH",
									((String) globalMap.get("tFileList_3_CURRENT_FILEPATH")));

							row9 = row8;

							tos_count_tFileDelete_3++;

							/**
							 * [tFileDelete_3 main ] stop
							 */

							/**
							 * [tFileDelete_3 process_data_begin ] start
							 */

							currentComponent = "tFileDelete_3";

							/**
							 * [tFileDelete_3 process_data_begin ] stop
							 */

							/**
							 * [tLogRow_6 main ] start
							 */

							currentComponent = "tLogRow_6";

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row9", "tFileDelete_3", "tFileDelete_3", "tFileDelete", "tLogRow_6", "tLogRow_6",
									"tLogRow"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row9 - " + (row9 == null ? "" : row9.toLogString()));
							}

///////////////////////		

							String[] row_tLogRow_6 = new String[3];

							if (row9.basename != null) { //
								row_tLogRow_6[0] = String.valueOf(row9.basename);

							} //

							if (row9.size != null) { //
								row_tLogRow_6[1] = String.valueOf(row9.size);

							} //

							if (row9.mtime != null) { //
								row_tLogRow_6[2] = String.valueOf(row9.mtime);

							} //

							util_tLogRow_6.addRow(row_tLogRow_6);
							nb_line_tLogRow_6++;
							log.info("tLogRow_6 - Content of row " + nb_line_tLogRow_6 + ": "
									+ TalendString.unionString("|", row_tLogRow_6));
//////

//////                    

///////////////////////    			

							tos_count_tLogRow_6++;

							/**
							 * [tLogRow_6 main ] stop
							 */

							/**
							 * [tLogRow_6 process_data_begin ] start
							 */

							currentComponent = "tLogRow_6";

							/**
							 * [tLogRow_6 process_data_begin ] stop
							 */

							/**
							 * [tLogRow_6 process_data_end ] start
							 */

							currentComponent = "tLogRow_6";

							/**
							 * [tLogRow_6 process_data_end ] stop
							 */

							/**
							 * [tFileDelete_3 process_data_end ] start
							 */

							currentComponent = "tFileDelete_3";

							/**
							 * [tFileDelete_3 process_data_end ] stop
							 */

							/**
							 * [tLogRow_5 process_data_end ] start
							 */

							currentComponent = "tLogRow_5";

							/**
							 * [tLogRow_5 process_data_end ] stop
							 */

						} // End of branch "copyOfout1_0"

					} // G_TM_M_280 close main tMap filter for table 'row7'

					/**
					 * [tMap_3 process_data_end ] start
					 */

					currentComponent = "tMap_3";

					/**
					 * [tMap_3 process_data_end ] stop
					 */

					/**
					 * [tFileProperties_3 process_data_end ] start
					 */

					currentComponent = "tFileProperties_3";

					/**
					 * [tFileProperties_3 process_data_end ] stop
					 */

					/**
					 * [tFileProperties_3 end ] start
					 */

					currentComponent = "tFileProperties_3";

					if (log.isDebugEnabled())
						log.debug("tFileProperties_3 - " + ("Done."));

					ok_Hash.put("tFileProperties_3", true);
					end_Hash.put("tFileProperties_3", System.currentTimeMillis());

					/**
					 * [tFileProperties_3 end ] stop
					 */

					/**
					 * [tMap_3 end ] start
					 */

					currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
// ###############################      
					log.debug("tMap_3 - Written records count in the table 'copyOfout1_0': " + count_copyOfout1_0_tMap_3
							+ ".");

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row7", 2, 0,
							"tFileProperties_3", "tFileProperties_3", "tFileProperties", "tMap_3", "tMap_3", "tMap",
							"output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tMap_3 - " + ("Done."));

					ok_Hash.put("tMap_3", true);
					end_Hash.put("tMap_3", System.currentTimeMillis());

					/**
					 * [tMap_3 end ] stop
					 */

					/**
					 * [tLogRow_5 end ] start
					 */

					currentComponent = "tLogRow_5";

//////

					java.io.PrintStream consoleOut_tLogRow_5 = null;
					if (globalMap.get("tLogRow_CONSOLE") != null) {
						consoleOut_tLogRow_5 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
					} else {
						consoleOut_tLogRow_5 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
						globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_5);
					}

					consoleOut_tLogRow_5.println(util_tLogRow_5.format().toString());
					consoleOut_tLogRow_5.flush();
//////
					globalMap.put("tLogRow_5_NB_LINE", nb_line_tLogRow_5);
					if (log.isInfoEnabled())
						log.info("tLogRow_5 - " + ("Printed row count: ") + (nb_line_tLogRow_5) + ("."));

///////////////////////    			

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "copyOfout1_0", 2, 0,
							"tMap_3", "tMap_3", "tMap", "tLogRow_5", "tLogRow_5", "tLogRow", "output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tLogRow_5 - " + ("Done."));

					ok_Hash.put("tLogRow_5", true);
					end_Hash.put("tLogRow_5", System.currentTimeMillis());

					/**
					 * [tLogRow_5 end ] stop
					 */

					/**
					 * [tFileDelete_3 end ] start
					 */

					currentComponent = "tFileDelete_3";

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row8", 2, 0,
							"tLogRow_5", "tLogRow_5", "tLogRow", "tFileDelete_3", "tFileDelete_3", "tFileDelete",
							"output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tFileDelete_3 - " + ("Done."));

					ok_Hash.put("tFileDelete_3", true);
					end_Hash.put("tFileDelete_3", System.currentTimeMillis());

					/**
					 * [tFileDelete_3 end ] stop
					 */

					/**
					 * [tLogRow_6 end ] start
					 */

					currentComponent = "tLogRow_6";

//////

					java.io.PrintStream consoleOut_tLogRow_6 = null;
					if (globalMap.get("tLogRow_CONSOLE") != null) {
						consoleOut_tLogRow_6 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
					} else {
						consoleOut_tLogRow_6 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
						globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_6);
					}

					consoleOut_tLogRow_6.println(util_tLogRow_6.format().toString());
					consoleOut_tLogRow_6.flush();
//////
					globalMap.put("tLogRow_6_NB_LINE", nb_line_tLogRow_6);
					if (log.isInfoEnabled())
						log.info("tLogRow_6 - " + ("Printed row count: ") + (nb_line_tLogRow_6) + ("."));

///////////////////////    			

					if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row9", 2, 0,
							"tFileDelete_3", "tFileDelete_3", "tFileDelete", "tLogRow_6", "tLogRow_6", "tLogRow",
							"output")) {
						talendJobLogProcess(globalMap);
					}

					if (log.isDebugEnabled())
						log.debug("tLogRow_6 - " + ("Done."));

					ok_Hash.put("tLogRow_6", true);
					end_Hash.put("tLogRow_6", System.currentTimeMillis());

					/**
					 * [tLogRow_6 end ] stop
					 */

					if (execStat) {
						runStat.updateStatOnConnection("iterate4", 2, "exec" + NB_ITERATE_tFileProperties_3);
					}

					/**
					 * [tFileList_3 process_data_end ] start
					 */

					currentComponent = "tFileList_3";

					/**
					 * [tFileList_3 process_data_end ] stop
					 */

					/**
					 * [tFileList_3 end ] start
					 */

					currentComponent = "tFileList_3";

				}
				globalMap.put("tFileList_3_NB_FILE", NB_FILEtFileList_3);

				log.info("tFileList_3 - File or directory count : " + NB_FILEtFileList_3);

				if (log.isDebugEnabled())
					log.debug("tFileList_3 - " + ("Done."));

				ok_Hash.put("tFileList_3", true);
				end_Hash.put("tFileList_3", System.currentTimeMillis());

				/**
				 * [tFileList_3 end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileList_3 finally ] start
				 */

				currentComponent = "tFileList_3";

				/**
				 * [tFileList_3 finally ] stop
				 */

				/**
				 * [tFileProperties_3 finally ] start
				 */

				currentComponent = "tFileProperties_3";

				/**
				 * [tFileProperties_3 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tLogRow_5 finally ] start
				 */

				currentComponent = "tLogRow_5";

				/**
				 * [tLogRow_5 finally ] stop
				 */

				/**
				 * [tFileDelete_3 finally ] start
				 */

				currentComponent = "tFileDelete_3";

				/**
				 * [tFileDelete_3 finally ] stop
				 */

				/**
				 * [tLogRow_6 finally ] start
				 */

				currentComponent = "tLogRow_6";

				/**
				 * [tLogRow_6 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileList_3_SUBPROCESS_STATE", 1);
	}

	public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", "talendJobLog");
		org.slf4j.MDC.put("_subJobPid", TalendString.getAsciiRandomString(6));

		String iterateId = "";

		String currentComponent = "";
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [talendJobLog begin ] start
				 */

				ok_Hash.put("talendJobLog", false);
				start_Hash.put("talendJobLog", System.currentTimeMillis());

				currentComponent = "talendJobLog";

				int tos_count_talendJobLog = 0;

				for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
					org.talend.job.audit.JobContextBuilder builder_talendJobLog = org.talend.job.audit.JobContextBuilder
							.create().jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
							.custom("process_id", jcm.pid).custom("thread_id", jcm.tid).custom("pid", pid)
							.custom("father_pid", fatherPid).custom("root_pid", rootPid);
					org.talend.logging.audit.Context log_context_talendJobLog = null;

					if (jcm.log_type == JobStructureCatcherUtils.LogType.PERFORMANCE) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.sourceId(jcm.sourceId)
								.sourceLabel(jcm.sourceLabel).sourceConnectorType(jcm.sourceComponentName)
								.targetId(jcm.targetId).targetLabel(jcm.targetLabel)
								.targetConnectorType(jcm.targetComponentName).connectionName(jcm.current_connector)
								.rows(jcm.row_count).duration(duration).build();
						auditLogger_talendJobLog.flowExecution(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBSTART) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).build();
						auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBEND) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).duration(duration)
								.status(jcm.status).build();
						auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.RUNCOMPONENT) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment)
								.connectorType(jcm.component_name).connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label).build();
						auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWINPUT) {// log current component
																							// input line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWOUTPUT) {// log current component
																								// output/reject line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBERROR) {
						java.lang.Exception e_talendJobLog = jcm.exception;
						if (e_talendJobLog != null) {
							try (java.io.StringWriter sw_talendJobLog = new java.io.StringWriter();
									java.io.PrintWriter pw_talendJobLog = new java.io.PrintWriter(sw_talendJobLog)) {
								e_talendJobLog.printStackTrace(pw_talendJobLog);
								builder_talendJobLog.custom("stacktrace", sw_talendJobLog.getBuffer().substring(0,
										java.lang.Math.min(sw_talendJobLog.getBuffer().length(), 512)));
							}
						}

						if (jcm.extra_info != null) {
							builder_talendJobLog.connectorId(jcm.component_id).custom("extra_info", jcm.extra_info);
						}

						log_context_talendJobLog = builder_talendJobLog
								.connectorType(jcm.component_id.substring(0, jcm.component_id.lastIndexOf('_')))
								.connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label == null ? jcm.component_id : jcm.component_label)
								.build();

						auditLogger_talendJobLog.exception(log_context_talendJobLog);
					}

				}

				/**
				 * [talendJobLog begin ] stop
				 */

				/**
				 * [talendJobLog main ] start
				 */

				currentComponent = "talendJobLog";

				tos_count_talendJobLog++;

				/**
				 * [talendJobLog main ] stop
				 */

				/**
				 * [talendJobLog process_data_begin ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_begin ] stop
				 */

				/**
				 * [talendJobLog process_data_end ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog process_data_end ] stop
				 */

				/**
				 * [talendJobLog end ] start
				 */

				currentComponent = "talendJobLog";

				ok_Hash.put("talendJobLog", true);
				end_Hash.put("talendJobLog", System.currentTimeMillis());

				/**
				 * [talendJobLog end ] stop
				 */
			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendJobLog finally ] start
				 */

				currentComponent = "talendJobLog";

				/**
				 * [talendJobLog finally ] stop
				 */
			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "PRD";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	private final static java.util.Properties jobInfo = new java.util.Properties();
	private final static java.util.Map<String, String> mdcInfo = new java.util.HashMap<>();

	public static void main(String[] args) {
		final Cleaning_server Cleaning_serverClass = new Cleaning_server();

		int exitCode = Cleaning_serverClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Cleaning_server' - Done.");
		}

		System.exit(exitCode);
	}

	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if (path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
		readJobInfo(new java.io.File(BUILD_PATH));
	}

	private void readJobInfo(java.io.File jobInfoFile) {

		if (jobInfoFile.exists()) {
			try (java.io.InputStream is = new java.io.FileInputStream(jobInfoFile)) {
				jobInfo.load(is);
			} catch (IOException e) {

				log.debug("Read jobInfo.properties file fail: " + e.getMessage());

			}
		}
		log.info(String.format("Project name: %s\tJob name: %s\tGIT Commit ID: %s\tTalend Version: %s", projectName,
				jobName, jobInfo.getProperty("gitCommitId"), "8.0.1.20230418_1502-patch"));

	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (!"".equals(log4jLevel)) {

			if ("trace".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.TRACE);
			} else if ("debug".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.DEBUG);
			} else if ("info".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.INFO);
			} else if ("warn".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.WARN);
			} else if ("error".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.ERROR);
			} else if ("fatal".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.FATAL);
			} else if ("off".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.OFF);
			}
			org.apache.logging.log4j.core.config.Configurator
					.setLevel(org.apache.logging.log4j.LogManager.getRootLogger().getName(), log.getLevel());

		}

		getjobInfo();
		log.info("TalendJob: 'Cleaning_server' - Start.");

		java.util.Set<Object> jobInfoKeys = jobInfo.keySet();
		for (Object jobInfoKey : jobInfoKeys) {
			org.slf4j.MDC.put("_" + jobInfoKey.toString(), jobInfo.get(jobInfoKey).toString());
		}
		org.slf4j.MDC.put("_pid", pid);
		org.slf4j.MDC.put("_rootPid", rootPid);
		org.slf4j.MDC.put("_fatherPid", fatherPid);
		org.slf4j.MDC.put("_projectName", projectName);
		org.slf4j.MDC.put("_startTimestamp", java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC)
				.format(java.time.format.DateTimeFormatter.ISO_INSTANT));
		org.slf4j.MDC.put("_jobRepositoryId", "_6LLuEPO2Eeu0C69wHYCB7w");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2023-06-02T18:09:35.687126400Z");

		java.lang.management.RuntimeMXBean mx = java.lang.management.ManagementFactory.getRuntimeMXBean();
		String[] mxNameTable = mx.getName().split("@"); //$NON-NLS-1$
		if (mxNameTable.length == 2) {
			org.slf4j.MDC.put("_systemPid", mxNameTable[0]);
		} else {
			org.slf4j.MDC.put("_systemPid", String.valueOf(java.lang.Thread.currentThread().getId()));
		}

		if (enableLogStash) {
			java.util.Properties properties_talendJobLog = new java.util.Properties();
			properties_talendJobLog.setProperty("root.logger", "audit");
			properties_talendJobLog.setProperty("encoding", "UTF-8");
			properties_talendJobLog.setProperty("application.name", "Talend Studio");
			properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
			properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
			properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
			properties_talendJobLog.setProperty("log.appender", "file");
			properties_talendJobLog.setProperty("appender.file.path", "audit.json");
			properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
			properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
			properties_talendJobLog.setProperty("host", "false");

			System.getProperties().stringPropertyNames().stream().filter(it -> it.startsWith("audit.logger."))
					.forEach(key -> properties_talendJobLog.setProperty(key.substring("audit.logger.".length()),
							System.getProperty(key)));

			org.apache.logging.log4j.core.config.Configurator
					.setLevel(properties_talendJobLog.getProperty("root.logger"), org.apache.logging.log4j.Level.DEBUG);

			auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory
					.createJobAuditLogger(properties_talendJobLog);
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		org.slf4j.MDC.put("_pid", pid);

		if (rootPid == null) {
			rootPid = pid;
		}

		org.slf4j.MDC.put("_rootPid", rootPid);

		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}
		org.slf4j.MDC.put("_fatherPid", fatherPid);

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		try {
			java.util.Dictionary<String, Object> jobProperties = null;
			if (inOSGi) {
				jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

				if (jobProperties != null && jobProperties.get("context") != null) {
					contextStr = (String) jobProperties.get("context");
				}
			}
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Cleaning_server.class.getClassLoader()
					.getResourceAsStream("teste_jp/cleaning_server_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Cleaning_server.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						if (inOSGi && jobProperties != null) {
							java.util.Enumeration<String> keys = jobProperties.keys();
							while (keys.hasMoreElements()) {
								String propKey = keys.nextElement();
								if (defaultProps.containsKey(propKey)) {
									defaultProps.put(propKey, (String) jobProperties.get(propKey));
								}
							}
						}
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
					context.setContextType("folder_directory", "id_String");
					if (context.getStringValue("folder_directory") == null) {
						context.folder_directory = null;
					} else {
						context.folder_directory = (String) context.getProperty("folder_directory");
					}
					context.setContextType("folder_directory2", "id_String");
					if (context.getStringValue("folder_directory2") == null) {
						context.folder_directory2 = null;
					} else {
						context.folder_directory2 = (String) context.getProperty("folder_directory2");
					}
					context.setContextType("file_mask", "id_String");
					if (context.getStringValue("file_mask") == null) {
						context.file_mask = null;
					} else {
						context.file_mask = (String) context.getProperty("file_mask");
					}
					context.setContextType("file_mask2", "id_String");
					if (context.getStringValue("file_mask2") == null) {
						context.file_mask2 = null;
					} else {
						context.file_mask2 = (String) context.getProperty("file_mask2");
					}
					context.setContextType("day", "id_Integer");
					if (context.getStringValue("day") == null) {
						context.day = null;
					} else {
						try {
							context.day = routines.system.ParserUtils.parseTo_Integer(context.getProperty("day"));
						} catch (NumberFormatException e) {
							log.warn(String.format("Null value will be used for context parameter %s: %s", "day",
									e.getMessage()));
							context.day = null;
						}
					}
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("folder_directory")) {
				context.folder_directory = (String) parentContextMap.get("folder_directory");
			}
			if (parentContextMap.containsKey("folder_directory2")) {
				context.folder_directory2 = (String) parentContextMap.get("folder_directory2");
			}
			if (parentContextMap.containsKey("file_mask")) {
				context.file_mask = (String) parentContextMap.get("file_mask");
			}
			if (parentContextMap.containsKey("file_mask2")) {
				context.file_mask2 = (String) parentContextMap.get("file_mask2");
			}
			if (parentContextMap.containsKey("day")) {
				context.day = (Integer) parentContextMap.get("day");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, ContextProperties.class, parametersToEncrypt));

		org.slf4j.MDC.put("_context", contextStr);
		log.info("TalendJob: 'Cleaning_server' - Started.");
		mdcInfo.putAll(org.slf4j.MDC.getCopyOfContextMap());

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		if (enableLogStash) {
			talendJobLog.addJobStartMessage();
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileList_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileList_1) {
			globalMap.put("tFileList_1_SUBPROCESS_STATE", -1);

			e_tFileList_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Cleaning_server");
		}
		if (enableLogStash) {
			talendJobLog.addJobEndMessage(startTime, end, status);
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");
		resumeUtil.flush();

		org.slf4j.MDC.remove("_subJobName");
		org.slf4j.MDC.remove("_subJobPid");
		org.slf4j.MDC.remove("_systemPid");
		log.info("TalendJob: 'Cleaning_server' - Finished - status: " + status + " returnCode: " + returnCode);

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 305453 characters generated by Talend Real-time Big Data Platform on the 2 de
 * junho de 2023 15:09:35 BRT
 ************************************************************************************************/