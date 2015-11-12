package com.velocity.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityTempalte {

	private VelocityEngine ve;
	private Template template;
	private Properties props;
	private String encoding;
	private String clazzName;
	private Map<String, String> className;

	public VelocityTempalte() {

	}

	public VelocityTempalte(String clazzName, String encoding) {
		this.ve = new VelocityEngine();
		this.ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		this.ve.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		this.ve.init();

		this.props = getProperties();
		this.clazzName = clazzName;
		this.encoding = encoding;
		this.className = getClassName();
	}

	private Map<String, String> getClassName() {
		Map<String, String> map = new HashMap<String, String>();
		int index = this.clazzName.lastIndexOf(".") + 1;
		String name1 = this.clazzName.substring(index);
		String name2 = String.valueOf(name1.charAt(0)).toLowerCase() + name1.substring(1);
		String packageName = this.clazzName.substring(0, this.clazzName.indexOf(".entity"));
		map.put("name1", name1);
		map.put("name2", name2);
		map.put("packageName", packageName);
		return map;
	}
	
	public static void main(String[] args) {
		VelocityTempalte vt = new VelocityTempalte("com.root.helper.entity.SuperFile", "UTF-8");
		vt.createTemplate();
		//File file = new File("D:\\work\\git-work\\building-core\\src\\main\\resources\\hessian-servlet.xml");  //我的文件在C盘下
		//System.out.println(readToString(file));
	}

	public void createTemplate() {
		try {
			//createTemplateEntity();
			//createTemplateParam();
			//createTemplateMapper();
			//createTemplateMapperXml("SUPER_FILE");
			//createTemplateDao();
			//createTemplateService();
			//createTemplateController();
			//createTemplateCondition();
			createTemplateJSP("SU文件");
			//createTemplateConf();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createTemplateConf() {
		String name1 = className.get("name1");
		String name2 = className.get("name2");
		
		StringBuffer sb = new StringBuffer("");
		String path = props.getProperty("path_hessian_service");
		File file = new File(path + "hessian-servlet.xml"); 
		String content = readToString(file);
		if (!content.contains(name2 + "Service")) {
			sb.append("<bean name=\"/" + name2 + "Service\" class=\"org.springframework.remoting.caucho.HessianServiceExporter\">\n");
			sb.append("        <property name=\"service\" ref=\"" + name2 + "Service\" />\n");
			sb.append("        <property name=\"serviceInterface\" value=\"com.house.building.service.I" + name1 + "Service\" />\n");
			sb.append("    </bean>\n");
			sb.append("    <!-- hessian-servlet-conf -->");
			content = content.replace("<!-- hessian-servlet-conf -->", sb.toString());
			writeJavaFile(path + "hessian-servlet.xml", content);
		}
		
		path = props.getProperty("path_application_web");
		file = new File(path + "application-web.xml");
		content = readToString(file);
		if (!content.contains(name2 + "Service")) {
			sb = new StringBuffer("");
			sb.append("<bean id=\"" + name2 + "Service\" class=\"org.springframework.remoting.caucho.HessianProxyFactoryBean\">\n");
			sb.append("        <property name=\"serviceUrl\">\n");
			sb.append("            <value>http://localhost:8085/hessian/" + name2 + "Service</value>\n");
			sb.append("        </property>\n");
			sb.append("        <property name=\"serviceInterface\">\n");
			sb.append("            <value>com.house.building.service.I" + name1 + "Service</value>\n");
			sb.append("        </property>\n");
			sb.append("    </bean>\n");
			sb.append("    <!-- application-web-conf -->");
			content = content.replace("<!-- application-web-conf -->", sb.toString());
			writeJavaFile(path + "application-web.xml", content);
		}
		
		path = props.getProperty("path_sitemesh");
		file = new File(path + "sitemesh3.xml");
		content = readToString(file);
		if (!content.contains("manage/" + name2)) {
			sb = new StringBuffer("");
			sb.append("<mapping path=\"/manage/" + name2 + "\" decorator=\"/WEB-INF/layout/mainLayout.jsp\"/>\n");
			sb.append("    <!-- sitemesh-conf -->");
			content = content.replace("<!-- sitemesh-conf -->", sb.toString());
			writeJavaFile(path + "sitemesh3.xml", content);
		}
	}

	private void createTemplateEntity() {
		template = ve.getTemplate("template.entity.vm", encoding);
		VelocityContext context = new VelocityContext();

		context.put("package", className.get("packageName"));
		context.put("Entity", className.get("name1"));

		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(props.getProperty("path_entity") + className.get("name1")
				+ ".java", writer.toString());
	}
	
	private void createTemplateParam() {
		template = ve.getTemplate("template.param.vm", encoding);
		VelocityContext context = new VelocityContext();

		context.put("package", className.get("packageName"));
		context.put("Entity", className.get("name1"));

		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(props.getProperty("path_param") + className.get("name1")
				+ "QueryParam.java", writer.toString());
	}

	private void createTemplateCondition() throws Exception {
		template = ve.getTemplate("template.condition.vm", encoding);
		VelocityContext context = new VelocityContext();
		
		String packageName = props.getProperty("package_condition");
		context.put("package", packageName);
		EntityReflect entityReflect = new EntityReflect(clazzName);
		List<EntityAnnotation> fileds = entityReflect.getFields();
		
		StringBuilder attributes = new StringBuilder("");
		StringBuilder methods = new StringBuilder("");
		
		EntityAnnotation filed = null;
		for (int i = 0; i < fileds.size(); i++) {
			filed = fileds.get(i);

			if (filed.isCondition()) {
				attributes.append("private String ").append(filed.getName())
						.append(";\n");
				methods.append("public String ")
						.append(entityReflect.getMethodName(filed.getName()))
						.append("() {\n");
				methods.append("	return ").append(filed.getName())
						.append(";\n").append("}\n");
				methods.append("public void ")
						.append(entityReflect.setMethodName(filed.getName()))
						.append("(String ").append(filed.getName())
						.append(") {\n");
				methods.append("	this.").append(filed.getName()).append(" = ")
						.append(filed.getName()).append(";\n}\n");
			}
		}
		
		if (attributes.toString().length() > 0) {
			context.put("attributes", attributes.toString());
			context.put("methods", methods.toString());
			context.put("Entity", className.get("name1"));
			
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			writeJavaFile(props.getProperty("path_condition") + className.get("name1")
					+ "Condition.java", writer.toString());
		}
	}

	private void createTemplateJSP(String title) throws Exception {
		//template = ve.getTemplate("template.web.list.vm", encoding);
		template = ve.getTemplate("template.jsp.list.vm", encoding);
		VelocityContext context = new VelocityContext();
		/*EntityReflect entityReflect = new EntityReflect(clazzName);
		List<EntityAnnotation> fileds = entityReflect.getFields();
		
		StringBuilder ths = new StringBuilder("");
		StringBuilder searchs = new StringBuilder("");
		StringBuilder elements = new StringBuilder("");
		StringBuilder conditions = new StringBuilder("");

		EntityAnnotation filed = null;
		String comment = null, width = null;
		
		for (int i = 0; i < fileds.size(); i++) {
			filed = fileds.get(i);
			comment = filed.getComment();
			
			if (filed.isCondition()) {
				searchs.append("<span>").append(comment).append(":</span>\n");
				searchs.append("<input id=\"").append(filed.getName()).append("\" style=\"width:100px\"");
				if ("TIMESTAMP".equals(filed.getJdbcType())) {
					searchs.append(" class=\"easyui-datebox\"");
				}
				searchs.append(" />\n");
				
				conditions.append(filed.getName()).append(": $('#").append(filed.getName()).append("').val(),\n");
			}
			
			width = filed.getWidth();
			if (width != null) {
				ths.append("<th field=\"").append(filed.getName()).append("\"");
				if (StringUtils.isNotEmpty(width)) {
					ths.append(" width=\"").append(width).append("\"");
				}
				ths.append(">");
				ths.append(comment).append("</th>\n");	
			}
			
			String type = filed.getType();
			if (StringUtils.isNotEmpty(type)) {
				elements.append("<tr>\n<td align=\"right\" width=\"80\"");
				elements.append("><label>").append(comment).append("：</label></td>\n");
				elements.append("<td><").append(type).append(" name=\"").append(filed.getName()).append("\"");
				
				if (StringUtils.isNotEmpty(filed.getPattern())) {
					elements.append(" class=\"").append(filed.getPattern()).append("\"");
				}
				if (filed.isRequired()) {
					elements.append(" required=\"true\"");
				}
				if ("textarea".equals(type)) {
					elements.append(" style=\"width:330px;height:80px;\"");
				}
				elements.append(">");
				if ("textarea".equals(type)) {
					elements.append("</textarea>");
				}
				elements.append("</td></tr>\n");
			}
		}
		
		context.put("ths", ths.toString());
		context.put("entity", className.get("name2"));
		context.put("Entity", className.get("name1"));
		String titleName = entityReflect.getTitleName();
		context.put("title", titleName);
		
		int length = searchs.toString().length();
		if (length > 0) {
			searchs.append("<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-search\" onclick=\"doSearch()\">Search</a>");
			searchs.insert(0, "<div>").append("</div>");
		}
		context.put("searchs", searchs.toString());
		context.put("elements", elements.toString());
		
		length = conditions.toString().length();
		if (length > 0) {
			conditions.delete(length-2, length-1);
		}
		context.put("conditions", conditions.toString());*/
		context.put("entity", className.get("name2"));
		context.put("title", title);

		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(props.getProperty("path_jsp") + className.get("name2") + "-list.jsp", writer.toString());
		
		template = ve.getTemplate("template.jsp.add.vm", encoding);
		writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(props.getProperty("path_jsp") + className.get("name2") + "-add.jsp", writer.toString());
		
		template = ve.getTemplate("template.jsp.edit.vm", encoding);
		writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(props.getProperty("path_jsp") + className.get("name2") + "-edit.jsp", writer.toString());
	}

	private void createTemplateMapper() {
		template = ve.getTemplate("template.mapper.vm", encoding);
		VelocityContext context = new VelocityContext();

		context.put("package", className.get("packageName"));
		context.put("Entity", className.get("name1"));
		context.put("entity", className.get("name2"));

		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(props.getProperty("path_mapper") + "I" + className.get("name1")
				+ "Mapper.java", writer.toString());
	}

	private void createTemplateMapperXml(String table) throws Exception {
		template = ve.getTemplate("template.mapper.xml.vm", encoding);
		VelocityContext context = new VelocityContext();

		context.put("package", className.get("packageName"));
		context.put("Entity", className.get("name1"));
		context.put("entity", className.get("name2"));
		/*EntityReflect entityReflect = new EntityReflect(clazzName);

		StringBuilder results = new StringBuilder("");
		StringBuilder columns = new StringBuilder("");
		StringBuilder values = new StringBuilder("");
		StringBuilder sets = new StringBuilder("");
		StringBuilder wheres = new StringBuilder("");
		
		List<EntityAnnotation> fileds = entityReflect.getFields();
		EntityAnnotation filed = null;

		for (int i = 0; i < fileds.size(); i++) {
			filed = fileds.get(i);
			results.append("<result column=\"").append(filed.getColumn())
					.append("\" ");
			results.append("property=\"").append(filed.getName()).append("\" ");
			results.append("jdbcType=\"").append(filed.getJdbcType())
					.append("\" />");
			if (i != fileds.size() - 1)
				results.append("\n");
			
			columns.append(filed.getColumn());
			if (i != fileds.size() - 1)
				columns.append(", ");
			
			values.append("#{").append(filed.getName()).append(", ");
			values.append("jdbcType=").append(filed.getJdbcType()).append("}");
			if (i != fileds.size() - 1)
				values.append(",\n");
			
			String type = filed.getType();
			if (StringUtils.isNotEmpty(type)) {
				sets.append("<if test=\"").append(filed.getName()).append(" != null\">\n");
				sets.append("	").append(filed.getName()).append(" = #{").append(filed.getName()).append(", jdbcType=").append(filed.getJdbcType()).append("},\n");
				sets.append("</if>\n");
			}
			
			if (filed.isCondition()) {
				wheres.append("<if test=\"").append(filed.getName()).append(" != null and ").append(filed.getName()).append(" != ''\">\n");
				wheres.append("	and ").append(filed.getColumn()).append(" = #{").append(filed.getName()).append(", jdbcType=").append(filed.getJdbcType()).append("}\n");
				wheres.append("</if>\n");
			}
		}
		context.put("results", results.toString());
		context.put("columns", columns.toString());
		context.put("values", values.toString());
		context.put("sets", sets.toString());
		
		int length = wheres.toString().length();
		if (length > 0) {
			wheres.insert(0, "<where>\n").append("</where>");
		}
		context.put("wheres", wheres.toString());
		String tableName = entityReflect.getTableName();*/
		context.put("table", table);

		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(
				props.getProperty("path_mapper_xml") + className.get("name1")
						+ "Mapper.xml", writer.toString());
	}

	private void createTemplateDao() {
		template = ve.getTemplate("template.dao.vm", encoding);
		VelocityContext context = new VelocityContext();

		context.put("package", className.get("packageName"));
		context.put("Entity", className.get("name1"));
		context.put("entity", className.get("name2"));

		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(props.getProperty("path_dao") + "I" + className.get("name1")
				+ "Dao.java", writer.toString());

		template = ve.getTemplate("template.dao.impl.vm", encoding);
		context.put("package", className.get("packageName"));

		writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(
				props.getProperty("path_dao_impl") + className.get("name1")
						+ "DaoImpl.java", writer.toString());
	}

	private void createTemplateService() {
		template = ve.getTemplate("template.service.vm", encoding);
		VelocityContext context = new VelocityContext();

		context.put("package", className.get("packageName"));
		context.put("Entity", className.get("name1"));
		context.put("entity", className.get("name2"));

		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(
				props.getProperty("path_service") + "I" + className.get("name1")
						+ "Service.java", writer.toString());

		template = ve.getTemplate("template.service.impl.vm", encoding);
		context.put("package", className.get("packageName"));

		writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(
				props.getProperty("path_service_impl") + className.get("name1")
						+ "ServiceImpl.java", writer.toString());
	}

	private void createTemplateController() {
		template = ve.getTemplate("template.controller.vm", encoding);
		VelocityContext context = new VelocityContext();

		context.put("package", className.get("packageName"));
		context.put("Entity", className.get("name1"));
		context.put("entity", className.get("name2"));

		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		writeJavaFile(
				props.getProperty("path_controller") + className.get("name1")
						+ "ManageController.java", writer.toString());
	}

	private Properties getProperties() {
		Properties props = new Properties();
		try {
			props.load(VelocityTempalte.class
					.getResourceAsStream("/template.helper.properties"));
		} catch (Exception e) {
			System.err.println("不能读取属性文件，请确保属性文件在你的classpath中");
		}
		return props;
	}

	private void writeJavaFile(String name, String content) {
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(name), "UTF-8"));
			bufferedWriter.write(content, 0, content.length());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedWriter != null) {
					bufferedWriter.flush();
					bufferedWriter.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static String readToString(File file) {
		Long filelength = file.length(); // 获取文件长度
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(filecontent);// 返回文件内容,默认编码
	}
}
