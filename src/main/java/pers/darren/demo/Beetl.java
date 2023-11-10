package pers.darren.demo;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.ResourceLoader;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.core.resource.StringTemplateResourceLoader;

import java.io.IOException;

public class Beetl
{

    public static void main(String[] args) {
        ResourceLoader resourceLoader = new ClasspathResourceLoader("/templates");

//        WebAppResourceLoader resourceLoader = new WebAppResourceLoader(this.getServletContext().getRealPath("/"), "UTF-8");
        try {
            Configuration cfg = Configuration.defaultConfiguration();
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            Template t = gt.getTemplate("hello.btl");
            t.binding("name", "beetl");
        t.renderTo(System.out);

        }catch (IOException e){

            e.printStackTrace();
        }
    }
    /**
     * 加载classpath下的资源
     * @throws Exception
     */
    static void classpathRL()throws Exception
    {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("/templates/hello.btl");
        t.binding("name", "beetl");
        String str = t.render();
        System.out.println(str);
    }

    /**
     * 加载文件系统下的资源
     * @throws Exception
     */
    static void filePathRL()throws Exception
    {
        String root = "E:\\pp\\src\\camel-web\\src\\main\\resources\\template";
        FileResourceLoader resourceLoader = new FileResourceLoader(root,"utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("hello.btl");
        t.binding("name", "beetl");
        String str = t.render();
        System.out.println(str);
    }

    /**
     * 直接构建字符串
     * @throws Exception
     */
    static void stringBeetl()throws Exception
    {
        StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate("hello,${name} \r\n你好：${name}");
        t.binding("name", "beetl");
        String str = t.render();
        System.out.println(str);
    }
}
