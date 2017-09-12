package com.sys.webframe.core.annotation;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sys.webframe.core.support.BaseController;

/**
 * @author stephen
 * 
 */
@RequestMapping("/caas/api")
public class ApiController extends BaseController {
	private static Map<String, ApiBean> apiMap = new HashMap<String, ApiBean>();

	@RequestMapping("help")
	public @ResponseBody
	Object help() {
		return apiMap.keySet();
	}

	@RequestMapping("apiinfo")
	public @ResponseBody
	Object apiInfo(String apiName) {
		return apiMap.get(apiName);
	}

	@PostConstruct
	private void getApiInfo() {
		log.info("开始扫描API");
		List<Class<?>> classes = getAllClassByPackage("com.yc.rm.caas.appserver.bus.controller");
		List<Class<?>> controllerList = new ArrayList<Class<?>>();
		for (Class<?> clas : classes) {
			if (clas.getName().contains("Controller")) {
				controllerList.add(clas);
			}
		}
		log.info("共扫描出" + controllerList.size() + "个对外接口");
		ApiBean apiBean = null;
		for (int i = 0; i < controllerList.size(); i++) {
			// 循环获取所有的类
			Class<?> c = controllerList.get(i);
			apiBean = new ApiBean();
			apiBean.setApiName(c.getName());
			// 获取类的所有方法
			Method[] methods = c.getDeclaredMethods();
			List<MethodBean> methodList = new ArrayList<MethodBean>();
			MethodBean methodBean = null;
			for (Method method : methods) {
				methodBean = new MethodBean();
				methodBean.setMethod(method.getName());
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					// 获取注解的具体类型
					Class<? extends Annotation> annotationType = annotation
							.annotationType();
					if (CaasAPI.class == annotationType) {
						CaasAPI anno = (CaasAPI) annotationType
								.cast(annotation);
						methodBean.setReqMsg(anno.TREQMSG());
						methodBean.setRspMsg(anno.TRSPMSG());
						methodBean.setErrorCode(anno.ERRORCODE());
					}
				}
				methodList.add(methodBean);
			}
			apiBean.setMethods(methodList);
			log.info("接口[" + apiBean.getApiName() + "]共包含"
					+ apiBean.getMethods().size() + "个对外方法");
			apiMap.put(apiBean.getApiName(), apiBean);
		}
		log.info("接口扫描完毕");
	}

	public static void main(String[] args) throws Exception {
		System.out.println("开始扫描API");
		List<Class<?>> classes = getAllClassByPackage("com.yc.rm.caas.appserver.bus.controller");
		List<Class<?>> controllerList = new ArrayList<Class<?>>();
		for (Class<?> clas : classes) {
			if (clas.getName().contains("Controller")) {
				controllerList.add(clas);
			}
		}
		System.out.println("共扫描出" + controllerList.size() + "个对外接口");
		ApiBean apiBean = null;
		for (int i = 0; i < controllerList.size(); i++) {
			// 循环获取所有的类
			Class<?> c = controllerList.get(i);
			apiBean = new ApiBean();
			Annotation[] classAnno = c.getAnnotations();
			for (Annotation annotation : classAnno) {
				// 获取注解的具体类型
				Class<? extends Annotation> annotationType = annotation
						.annotationType();
				if (RequestMapping.class == annotationType) {
					RequestMapping anno = (RequestMapping) annotationType
							.cast(annotation);					
					apiBean.setApiName(anno.value()[0]);
				}
			}			
			// 获取类的所有方法
			Method[] methods = c.getDeclaredMethods();
			List<MethodBean> methodList = new ArrayList<MethodBean>();
			MethodBean methodBean = null;
			for (Method method : methods) {
				methodBean = new MethodBean();
				methodBean.setMethod(method.getName());
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					// 获取注解的具体类型
					Class<? extends Annotation> annotationType = annotation
							.annotationType();
					if (RequestMapping.class == annotationType) {
						RequestMapping anno = (RequestMapping) annotationType
								.cast(annotation);					
						methodBean.setMethod(anno.value()[0]);
					}
					if (CaasAPI.class == annotationType) {
						CaasAPI anno = (CaasAPI) annotationType
								.cast(annotation);
						methodBean.setReqMsg(anno.TREQMSG());
						methodBean.setRspMsg(anno.TRSPMSG());
						methodBean.setErrorCode(anno.ERRORCODE());
					}
				}
				methodList.add(methodBean);
			}
			apiBean.setMethods(methodList);
			System.out.println("接口[" + apiBean.getApiName() + "]共包含"
					+ apiBean.getMethods().size() + "个对外方法");
			apiMap.put(apiBean.getApiName(), apiBean);
		}
		System.out.println("接口扫描完毕");
		System.out.println(apiMap.keySet());		
	}

	/**
	 * 取得某个包下所有实现这个接口的类
	 * */
	public static List<Class<?>> getAllClassByPackage(String packageName) {
		List<Class<?>> returnClassList = null;
		// 获取当前的包名
		// 获取当前包下以及子包下所以的类
		List<Class<?>> allClass = getClasses(packageName);
		if (allClass != null) {
			returnClassList = new ArrayList<Class<?>>();
			for (Class<?> classes : allClass) {
				// 判断是否是同一个接口
				returnClassList.add(classes);
			}
		}
		return returnClassList;
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 */
	public static List<Class<?>> getClasses(String packageName) {

		// 第一个class类的集合
		List<Class<?>> classes = new ArrayList<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader()
					.getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath,
							recursive, classes);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	public static void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, List<Class<?>> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(
						packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				try {
					// 添加到集合中去
					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
