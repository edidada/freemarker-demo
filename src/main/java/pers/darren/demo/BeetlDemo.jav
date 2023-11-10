package pers.darren.demo;


import org.beetl.core.resource.ClasspathResourceLoader;

import java.util.HashMap;
import java.util.Map;

public class BeetlDemo {
    public static void main(String[] args) {
        // 创建一个资源加载器，用于加载模板文件
        ResourceLoader resourceLoader = new ClasspathResourceLoader("/templates");

        // 获取一个模板实例
        Template template = resourceLoader.getTemplate("example.btl");

        // 设置模板变量
        Map<String, Object> data = new HashMap<>();
        data.put("name", "张三");
        data.put("age", 30);

        // 渲染模板并输出结果
        try {
            String result = template.render(data);
            System.out.println(result);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
