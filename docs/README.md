# freemarker-demo 项目文档

`freemarker-demo` 是一个基于 **FreeMarker** 模板引擎的 Java Web 实践示例工程（Maven `war` 项目，JDK 1.8）。
它演示了如何在独立程序与 Web Servlet 两种场景下使用 FreeMarker 渲染页面，并提供了若干 FreeMarker
配置的单例实现与中文编码处理方案。

## 主要功能

1. **FreeMarker 模板渲染**
   - 模板文件位于 `src/main/resources/view/templates/demo.ftlh`。
   - 演示了 `#if/#else` 条件判断、`#list` 列表遍历、共享变量（`homeUrl`）等常见用法。

2. **两种渲染方式**
   - `pers.darren.demo.Demo`：独立 `main` 方法，将渲染结果输出到控制台并写入本地 HTML 文件。
   - `pers.darren.demo.servlet.DemoServlet`（`@WebServlet("/demo")`）：在 Web 请求中将渲染结果
     直接写入 HTTP 响应，可通过浏览器访问 `/demo` 查看页面。

3. **FreeMarker 配置单例（三种实现）**
   - `common.ConfigSingleton`：基于 `enum` 的线程安全单例（推荐）。
   - `common.ConfigSingletonDL`：基于双重检查锁（Double-Checked Locking）的单例。
   - `common.Singletons`：早期实现，已被 `@Deprecated` 标记，仅作对比参考。
   - 统一配置：区域 `CHINA`、日期/时间格式、`UTF-8` 编码、`HTML_DEBUG_HANDLER` 异常处理器，
     以及模板加载路径 `/view/templates`。

4. **统一编码过滤**
   - `filter.EncodingFilter`（`@WebFilter("/*")`）：对所有请求/响应设置 `UTF-8` 编码，避免中文乱码。

5. **数据模型**
   - `model.Product`：产品信息实体（名称、价格、库存、描述、创建人/时间等），用于模板渲染示例。

## 工程结构

```
src/main/java/pers/darren/demo/
├── Demo.java                 # 独立程序渲染示例
├── servlet/DemoServlet.java  # Web Servlet 渲染示例
├── common/                   # FreeMarker 配置单例
│   ├── ConfigSingleton.java
│   ├── ConfigSingletonDL.java
│   └── Singletons.java
├── filter/EncodingFilter.java# UTF-8 编码过滤
└── model/Product.java        # 数据模型

src/main/resources/view/templates/demo.ftlh  # FreeMarker 模板
src/main/webapp/WEB-INF/web.xml              # Web 配置
```

## 构建与运行

使用 Maven 构建（需要 JDK 1.8+）：

```bash
mvn clean package
```

构建产物为 `target/freemarker-demo.war`，可部署到任意兼容 Servlet 4.0 的容器（如 Tomcat 8.5+）。

- 访问 `http://<host>:<port>/freemarker-demo/demo` 查看 Servlet 渲染效果。
- 直接运行 `Demo.main` 可在控制台/本地文件查看渲染结果。

## 持续集成

仓库通过 `.github/workflows/ci.yml` 配置了 GitHub Actions，在 `master` 与
`dependabot/maven/junit-junit-4.13.1` 分支推送时自动使用 JDK 8 执行 `mvn package` 编译构建。
