
package teste_jp.limpeza_kafka_0_1;

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
 * Job: Limpeza_kafka Purpose: <br>
 * Description: job para limpeza do kafka <br>
 * 
 * @author security, security
 * @version 8.0.1.20230418_1502-patch
 * @status
 */
public class Limpeza_kafka implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "Limpeza_kafka.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
			.getLogger(Limpeza_kafka.class);

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

			if (caminho_logs != null) {

				this.setProperty("caminho_logs", caminho_logs.toString());

			}

			if (caminho_windows != null) {

				this.setProperty("caminho_windows", caminho_windows.toString());

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

		public String caminho_logs;

		public String getCaminho_logs() {
			return this.caminho_logs;
		}

		public String caminho_windows;

		public String getCaminho_windows() {
			return this.caminho_windows;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Limpeza_kafka";
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
			"_p7zeQPpvEe2EusXe7keuYw", "0.1");
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
					Limpeza_kafka.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Limpeza_kafka.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileDelete_1_error(Exception exception, String errorComponent,
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

	public void tFileDelete_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileList_2_onSubJobError(exception, errorComponent, globalMap);
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

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

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

				/**
				 * [tFileList_1 begin ] start
				 */

				int NB_ITERATE_tFileDelete_1 = 0; // for statistics

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
							log4jParamters_tFileList_1.append("DIRECTORY" + " = " + "context.caminho_logs");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("LIST_MODE" + " = " + "BOTH");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("INCLUDSUBDIR" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("CASE_SENSITIVE" + " = " + "NO");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("ERROR" + " = " + "false");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("GLOBEXPRESSIONS" + " = " + "true");
							log4jParamters_tFileList_1.append(" | ");
							log4jParamters_tFileList_1.append("FILES" + " = " + "[]");
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

				String directory_tFileList_1 = context.caminho_logs;
				final java.util.List<String> maskList_tFileList_1 = new java.util.ArrayList<String>();
				final java.util.List<java.util.regex.Pattern> patternList_tFileList_1 = new java.util.ArrayList<java.util.regex.Pattern>();
				maskList_tFileList_1.add("*");
				for (final String filemask_tFileList_1 : maskList_tFileList_1) {
					String filemask_compile_tFileList_1 = filemask_tFileList_1;

					filemask_compile_tFileList_1 = org.apache.oro.text.GlobCompiler.globToPerl5(
							filemask_tFileList_1.toCharArray(), org.apache.oro.text.GlobCompiler.DEFAULT_MASK);

					java.util.regex.Pattern fileNamePattern_tFileList_1 = java.util.regex.Pattern
							.compile(filemask_compile_tFileList_1, java.util.regex.Pattern.CASE_INSENSITIVE);

					patternList_tFileList_1.add(fileNamePattern_tFileList_1);
				}
				int NB_FILEtFileList_1 = 0;

				final boolean case_sensitive_tFileList_1 = false;

				log.info("tFileList_1 - Starting to search for matching entries.");

				final java.util.List<java.io.File> list_tFileList_1 = new java.util.ArrayList<java.io.File>();
				final java.util.Set<String> filePath_tFileList_1 = new java.util.HashSet<String>();
				java.io.File file_tFileList_1 = new java.io.File(directory_tFileList_1);

				file_tFileList_1.listFiles(new java.io.FilenameFilter() {
					public boolean accept(java.io.File dir, String name) {
						java.io.File file = new java.io.File(dir, name);

						String fileName_tFileList_1 = file.getName();
						for (final java.util.regex.Pattern fileNamePattern_tFileList_1 : patternList_tFileList_1) {
							if (fileNamePattern_tFileList_1.matcher(fileName_tFileList_1).matches()) {
								if (!filePath_tFileList_1.contains(file.getAbsolutePath())) {
									list_tFileList_1.add(file);
									filePath_tFileList_1.add(file.getAbsolutePath());
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
					NB_ITERATE_tFileDelete_1++;

					if (execStat) {
						runStat.updateStatOnConnection("iterate1", 1, "exec" + NB_ITERATE_tFileDelete_1);
						// Thread.sleep(1000);
					}

					/**
					 * [tFileDelete_1 begin ] start
					 */

					ok_Hash.put("tFileDelete_1", false);
					start_Hash.put("tFileDelete_1", System.currentTimeMillis());

					currentComponent = "tFileDelete_1";

					int tos_count_tFileDelete_1 = 0;

					if (log.isDebugEnabled())
						log.debug("tFileDelete_1 - " + ("Start to work."));
					if (log.isDebugEnabled()) {
						class BytesLimit65535_tFileDelete_1 {
							public void limitLog4jByte() throws Exception {
								StringBuilder log4jParamters_tFileDelete_1 = new StringBuilder();
								log4jParamters_tFileDelete_1.append("Parameters:");
								log4jParamters_tFileDelete_1.append(
										"PATH" + " = " + "((String)globalMap.get(\"tFileList_1_CURRENT_FILEPATH\"))");
								log4jParamters_tFileDelete_1.append(" | ");
								log4jParamters_tFileDelete_1.append("FAILON" + " = " + "true");
								log4jParamters_tFileDelete_1.append(" | ");
								log4jParamters_tFileDelete_1.append("FOLDER_FILE" + " = " + "true");
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
					 * [tFileDelete_1 main ] start
					 */

					currentComponent = "tFileDelete_1";

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
					java.io.File path_tFileDelete_1 = new java.io.File(
							((String) globalMap.get("tFileList_1_CURRENT_FILEPATH")));
					if (path_tFileDelete_1.exists()) {
						if (path_tFileDelete_1.isFile()) {
							if (path_tFileDelete_1.delete()) {
								globalMap.put("tFileDelete_1_CURRENT_STATUS", "File deleted.");
								log.info("tFileDelete_1 - File : " + path_tFileDelete_1.getAbsolutePath()
										+ " is deleted.");
							} else {
								globalMap.put("tFileDelete_1_CURRENT_STATUS", "No file deleted.");
								throw new RuntimeException(
										"The file " + path_tFileDelete_1.getAbsolutePath() + " can't be deleted.");
							}
						} else if (path_tFileDelete_1.isDirectory()) {
							DeleteFoldertFileDelete_1 dftFileDelete_1 = new DeleteFoldertFileDelete_1();
							if (dftFileDelete_1.delete(path_tFileDelete_1)) {
								globalMap.put("tFileDelete_1_CURRENT_STATUS", "Path deleted.");
								log.info("tFileDelete_1 - Directory : " + path_tFileDelete_1.getAbsolutePath()
										+ " is deleted.");
							} else {
								globalMap.put("tFileDelete_1_CURRENT_STATUS", "No path deleted.");
								throw new RuntimeException("The directory " + path_tFileDelete_1.getAbsolutePath()
										+ " can not be deleted.");
							}
						}
					} else {
						globalMap.put("tFileDelete_1_CURRENT_STATUS", "File or path does not exist or is invalid.");
						throw new RuntimeException("File or path does not exist or is invalid.");
					}
					globalMap.put("tFileDelete_1_DELETE_PATH",
							((String) globalMap.get("tFileList_1_CURRENT_FILEPATH")));

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
					 * [tFileDelete_1 process_data_end ] start
					 */

					currentComponent = "tFileDelete_1";

					/**
					 * [tFileDelete_1 process_data_end ] stop
					 */

					/**
					 * [tFileDelete_1 end ] start
					 */

					currentComponent = "tFileDelete_1";

					if (log.isDebugEnabled())
						log.debug("tFileDelete_1 - " + ("Done."));

					ok_Hash.put("tFileDelete_1", true);
					end_Hash.put("tFileDelete_1", System.currentTimeMillis());

					/**
					 * [tFileDelete_1 end ] stop
					 */
					if (execStat) {
						runStat.updateStatOnConnection("iterate1", 2, "exec" + NB_ITERATE_tFileDelete_1);
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
				 * [tFileDelete_1 finally ] start
				 */

				currentComponent = "tFileDelete_1";

				/**
				 * [tFileDelete_1 finally ] stop
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

				/**
				 * [tFileList_2 begin ] start
				 */

				int NB_ITERATE_tFileDelete_2 = 0; // for statistics

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
							log4jParamters_tFileList_2.append("DIRECTORY" + " = " + "context.caminho_windows");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("LIST_MODE" + " = " + "FILES");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("INCLUDSUBDIR" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("CASE_SENSITIVE" + " = " + "YES");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("ERROR" + " = " + "false");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("GLOBEXPRESSIONS" + " = " + "true");
							log4jParamters_tFileList_2.append(" | ");
							log4jParamters_tFileList_2.append("FILES" + " = " + "[{FILEMASK=" + ("\".MDMP\"") + "}]");
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

				String directory_tFileList_2 = context.caminho_windows;
				final java.util.List<String> maskList_tFileList_2 = new java.util.ArrayList<String>();
				final java.util.List<java.util.regex.Pattern> patternList_tFileList_2 = new java.util.ArrayList<java.util.regex.Pattern>();
				maskList_tFileList_2.add(".MDMP");
				for (final String filemask_tFileList_2 : maskList_tFileList_2) {
					String filemask_compile_tFileList_2 = filemask_tFileList_2;

					filemask_compile_tFileList_2 = org.apache.oro.text.GlobCompiler.globToPerl5(
							filemask_tFileList_2.toCharArray(), org.apache.oro.text.GlobCompiler.DEFAULT_MASK);

					java.util.regex.Pattern fileNamePattern_tFileList_2 = java.util.regex.Pattern
							.compile(filemask_compile_tFileList_2);
					patternList_tFileList_2.add(fileNamePattern_tFileList_2);
				}
				int NB_FILEtFileList_2 = 0;

				final boolean case_sensitive_tFileList_2 = true;

				log.info("tFileList_2 - Starting to search for matching entries.");

				final java.util.List<java.io.File> list_tFileList_2 = new java.util.ArrayList<java.io.File>();
				final java.util.Set<String> filePath_tFileList_2 = new java.util.HashSet<String>();
				java.io.File file_tFileList_2 = new java.io.File(directory_tFileList_2);

				file_tFileList_2.listFiles(new java.io.FilenameFilter() {
					public boolean accept(java.io.File dir, String name) {
						java.io.File file = new java.io.File(dir, name);
						if (!file.isDirectory()) {

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
					NB_ITERATE_tFileDelete_2++;

					if (execStat) {
						runStat.updateStatOnConnection("iterate2", 1, "exec" + NB_ITERATE_tFileDelete_2);
						// Thread.sleep(1000);
					}

					/**
					 * [tFileDelete_2 begin ] start
					 */

					ok_Hash.put("tFileDelete_2", false);
					start_Hash.put("tFileDelete_2", System.currentTimeMillis());

					currentComponent = "tFileDelete_2";

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
								log4jParamters_tFileDelete_2.append("FAILON" + " = " + "true");
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
					 * [tFileDelete_2 main ] start
					 */

					currentComponent = "tFileDelete_2";

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
							log.info("tFileDelete_2 - File : " + file_tFileDelete_2.getAbsolutePath() + " is deleted.");
						} else {
							globalMap.put("tFileDelete_2_CURRENT_STATUS", "No file deleted.");
							throw new RuntimeException(
									"File " + file_tFileDelete_2.getAbsolutePath() + " can not be deleted.");
						}
					} else {
						globalMap.put("tFileDelete_2_CURRENT_STATUS", "File does not exist or is invalid.");
						throw new RuntimeException("File " + file_tFileDelete_2.getAbsolutePath()
								+ " does not exist or is invalid or is not a file.");
					}
					globalMap.put("tFileDelete_2_DELETE_PATH",
							((String) globalMap.get("tFileList_2_CURRENT_FILEPATH")));

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
					 * [tFileDelete_2 process_data_end ] start
					 */

					currentComponent = "tFileDelete_2";

					/**
					 * [tFileDelete_2 process_data_end ] stop
					 */

					/**
					 * [tFileDelete_2 end ] start
					 */

					currentComponent = "tFileDelete_2";

					if (log.isDebugEnabled())
						log.debug("tFileDelete_2 - " + ("Done."));

					ok_Hash.put("tFileDelete_2", true);
					end_Hash.put("tFileDelete_2", System.currentTimeMillis());

					/**
					 * [tFileDelete_2 end ] stop
					 */
					if (execStat) {
						runStat.updateStatOnConnection("iterate2", 2, "exec" + NB_ITERATE_tFileDelete_2);
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
				 * [tFileDelete_2 finally ] start
				 */

				currentComponent = "tFileDelete_2";

				/**
				 * [tFileDelete_2 finally ] stop
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
	public String contextStr = "DEV";
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
		final Limpeza_kafka Limpeza_kafkaClass = new Limpeza_kafka();

		int exitCode = Limpeza_kafkaClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'Limpeza_kafka' - Done.");
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
		log.info("TalendJob: 'Limpeza_kafka' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_p7zeQPpvEe2EusXe7keuYw");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2023-05-30T13:21:05.997015300Z");

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
			java.io.InputStream inContext = Limpeza_kafka.class.getClassLoader()
					.getResourceAsStream("teste_jp/limpeza_kafka_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Limpeza_kafka.class.getClassLoader()
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
					context.setContextType("caminho_logs", "id_String");
					if (context.getStringValue("caminho_logs") == null) {
						context.caminho_logs = null;
					} else {
						context.caminho_logs = (String) context.getProperty("caminho_logs");
					}
					context.setContextType("caminho_windows", "id_String");
					if (context.getStringValue("caminho_windows") == null) {
						context.caminho_windows = null;
					} else {
						context.caminho_windows = (String) context.getProperty("caminho_windows");
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
			if (parentContextMap.containsKey("caminho_logs")) {
				context.caminho_logs = (String) parentContextMap.get("caminho_logs");
			}
			if (parentContextMap.containsKey("caminho_windows")) {
				context.caminho_windows = (String) parentContextMap.get("caminho_windows");
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
		log.info("TalendJob: 'Limpeza_kafka' - Started.");
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
			System.out
					.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Limpeza_kafka");
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
		log.info("TalendJob: 'Limpeza_kafka' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 73670 characters generated by Talend Real-time Big Data Platform on the 30 de
 * maio de 2023 10:21:05 BRT
 ************************************************************************************************/