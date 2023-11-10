package pers.darren.demo;


import org.beetl.core.exception.BeetlException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
    public static void main(String[] args) throws BeetlException, TemplateLoadException, IOException {
        // 创建Beetl引擎实例
        BeetlEngine beetlEngine = new BeetlEngine();
        // 加载模板文件
        Template template = beetlEngine.getTemplate(new File("class.btl"));
        // 渲染模板，生成Java代码
        String code = template.render(new HashMap<String, Object>() {{
            put("package", "com.example");
            put("className", "Person");
        }});
        // 输出生成的Java代码到控制台
        System.out.println(code);
    }
}
