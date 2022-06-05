package com.enjoy.book.action;

import com.alibaba.fastjson.JSON;
import com.enjoy.book.bean.Book;
import com.enjoy.book.biz.BookBiz;
import com.enjoy.book.util.DBHelper;
import com.enjoy.book.util.DateHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


@WebServlet("/book.let")
public class BookServlet extends HttpServlet {
    BookBiz bookBiz = new BookBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    /**
     * /book.let?type=add 添加图书
     * /book.let?type=modifypre&id=xx 修改前准备
     * /book.let?type=modify        修改
     * /book.let?type=remove&id=xx    删除
     * /book.let?type=query&pageIndex=1 :分页查询(request:转发)
     * /book.let?type=details&id=xx   展示书籍详细信息
     * /book.let?type=doajax&name=xx  :使用ajax查询图书名对应的图书信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String type = req.getParameter("type");

        switch (type){
            case "add":
                try {
                    add(req, resp, out);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                    resp.sendError(500, "文件上传失败");
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendError(500, e.getMessage());
                }
                break;
            case "modifypre":
                //book.let?type=modifypre&id=xx
                long bookId = Long.parseLong(req.getParameter("id"));
                Book book = bookBiz.getById(bookId);
                req.setAttribute("book",book);
                //转发到book_modify.jsp页面
                req.getRequestDispatcher("book_modify.jsp").forward(req,resp);

                break;
            case "modify":
                try {
                    modify(req,resp,out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "remove":
                //1、获取删除的bookid
                long removeId = Long.parseLong(req.getParameter("id"));
                //2、调用biz删除方法
                try{
                    int count = bookBiz.remove(removeId);
                    if(count>0){
                        out.println("<script>alert('图书删除成功');location.href='book.let?type=query&pageIndex=1';</script>");
                    }
                    else{
                        out.println("<script>alert('图书删除失败');location.href='book.let?type=query&pageIndex=1';</script>");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"');location.href='book.let?type=query&pageIndex=1';</script>");
                }

                //3、提示+跳转-->out(查询的servlet)
                break;
            case "query":
                query(req,resp,out);
                break;
            case "details":
                details(req,resp,out);
                break;
            case "doajax":
                String name = req.getParameter("name");
                Book book2 = bookBiz.getByName(name);
                if(book2==null){
                    out.print("{}");//返回json一个null
                }
                else{
                    out.print(JSON.toJSONString(book2));
                }
                break;
            default:
                resp.sendError(404);//找不到资源
        }
    }

    /**
     * 修改图书信息的方法
     * @param req
     * @param resp
     * @param out
     */
    private void modify(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws Exception {
        //1、构建一个磁盘工厂，用以存放磁盘文件
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*9);
//        构建临时仓库，相当于一个文件夹
        File file = new File("c:\\temp");

        // TODO: 2022/5/1 直接创建一个文件夹行不行？？？
        if(!file.exists()){
            file.mkdir();//创建文件夹
        }
        factory.setRepository(file);
//      2、文件上传+表单数据
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
//      3、将请求解析成一个个FileItem(文件+表单元素) 文件项
        List<FileItem> fileItems = fileUpload.parseRequest(req);
//      4、遍历FileItem
        Book book = new Book();
        for(FileItem item:fileItems){
            //是表单元素
            if(item.isFormField()){
                //4.1 元素名称和用户填写的值 name：文城
                String name = item.getFieldName();
                String value = item.getString("utf-8");
                switch(name){
                    case "id":
                        book.setId(Long.parseLong(value));
                        break;
                    case "pic":
                        book.setPic(value);
                        break;
                    case "typeId":
                        book.setTypeId(Long.parseLong(value));
                        break;
                    case "name":
                        book.setName(value);
                        break;
                    case "price":
                        book.setPrice(Double.parseDouble(value));
                        break;
                    case "desc":
                        book.setDesc(value);
                        break;
                    case "publish":
                        book.setPublish(value);
                        break;
                    case "author":
                        book.setAuthor(value);
                        break;
                    case "stock":
                        book.setStock(Long.parseLong(value));
                        break;
                    case "address":
                        book.setAddress(value);
                        break;
                }
            }
            else{
                //图片的文件名
                //不上传文件时 -->fileName为空
                String fileName = item.getName();
                if(fileName.trim().length()>0)
                {
                    //获取后缀名 .png
                    String filterName = fileName.substring(fileName.lastIndexOf("."));
                    //修改文件名-->防止同一张照片重复上传
                    fileName = DateHelper.getImageName()+filterName;
                    //文件保存路径
                    //虚拟路径：Imags/covrt/xxx.png
                    //文件的读写:实际路径 D://xx -->虚拟路径: Images/cover对应的实际路径
                    String path = req.getServletContext().getRealPath("Images/cover");
                    //路径上添加上文件名
                    String filePath = path+"/"+fileName;
                    //数据库中的路径
                    String dbPath = "Images/cover/"+fileName;
                    book.setPic(dbPath);
                    //保存文件,此时的item是文件
                    item.write(new File(filePath));
                }

            }
        }
        //将信息保存到数据库中
        int count = bookBiz.modify(book);
        if(count>0) {
            //不想TypeServlet一样有application，这里需要先跳转到分页显示整理好数据后在跳转到book_list.jsp
            out.println("<script>alert('修改书籍成功');location.href='book.let?type=query&pageIndex=1';</script>");
        }
        else{
            out.println("<script>alert('修改书籍失败');location.href='book.let?type=query&pageIndex=1';</script>");
        }
    }


    /**
     * 查看读书详情的方法
     * @param req
     * @param resp
     * @param out
     */
    private void details(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        //1、获取图书的编号
        long bookId = Long.parseLong(req.getParameter("id"));
        out.println("the bookID is-->"+bookId);
        //2、根据编号获取图书对象
        Book book = bookBiz.getById(bookId);
        //3、将对象保存到req中
        req.setAttribute("book",book);
        //4、转发到jsp页面
        out.println(book.toString());
        req.getRequestDispatcher("book_details.jsp").forward(req,resp);
    }

    /**
     * book.let?type=query&pageIndex=1
     * 页数 biz
     * 当前页码 pageIndex =1
     * 存:request,转发
     */
    private void query(HttpServletRequest req, HttpServletResponse resp,PrintWriter out) throws ServletException, IOException {
        //1、获取信息(页数 页码 信息)
        int pageSize = 3;
        int pageCount = bookBiz.getPageCount(pageSize);
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        //防止页码越界
        if(pageIndex<1){
            pageIndex=1;
        }
        if(pageIndex>pageCount){
            pageIndex=pageCount;
        }
        List<Book> books = bookBiz.getByPage(pageIndex,pageSize);
        //2、存信息 -->request
        req.setAttribute("pageCount",pageCount);
        req.setAttribute("books",books);
        //pageIndex刻意不用存到req中，转发到jsp页面
        req.getRequestDispatcher("book_list.jsp?pageIndex="+pageIndex).forward(req,resp);



    }

    /**
     * 添加书籍
     * 1、enctype="multipart/form-data"：和以前不同
     * 获取表单元素：req.getParameter("name")  : error
     * 2、文件上传:图片文件从浏览器中上传到服务器，用到第三方工具(FileUpload+io)
     * 3、图片在用户端的存储地址为：实际路径，在服务器中的存储地址为：虚拟路径
     * @param req
     * @param resp
     * @param out
     */
    private void add(HttpServletRequest req, HttpServletResponse resp,PrintWriter out) throws Exception {
//      1、构建一个磁盘工厂，用以存放磁盘文件
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*9);
//        构建临时仓库，相当于一个文件夹
        File file = new File("c:\\temp");

        // TODO: 2022/5/1 直接创建一个文件夹行不行？？？ 
        if(!file.exists()){
            file.mkdir();//创建文件夹
        }
        factory.setRepository(file);
//      2、文件上传+表单数据
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
//      3、将请求解析成一个个FileItem(文件+表单元素) 文件项
        List<FileItem> fileItems = fileUpload.parseRequest(req);
//      4、遍历FileItem
        Book book = new Book();
        for(FileItem item:fileItems){
            //是表单元素
            if(item.isFormField()){
                //4.1 元素名称和用户填写的值 name：文城
                String name = item.getFieldName();
                String value = item.getString("utf-8");
                switch(name){
                    case "typeId":
                        book.setTypeId(Long.parseLong(value));
                        break;
                    case "name":
                        book.setName(value);
                        break;
                    case "price":
                        book.setPrice(Double.parseDouble(value));
                        break;
                    case "desc":
                        book.setDesc(value);
                        break;
                    case "publish":
                        book.setPublish(value);
                        break;
                    case "author":
                        book.setAuthor(value);
                        break;
                    case "stock":
                        book.setStock(Long.parseLong(value));
                        break;
                    case "address":
                        book.setAddress(value);
                        break;
                }
            }
            else{
                //图片的文件名
                String fileName = item.getName();
                //获取后缀名 .png
                String filterName = fileName.substring(fileName.lastIndexOf("."));
                //修改文件名-->防止同一张照片重复上传
                fileName = DateHelper.getImageName()+filterName;
                //文件保存路径
                //虚拟路径：Imags/covrt/xxx.png
                //文件的读写:实际路径 D://xx -->虚拟路径: Images/cover对应的实际路径
                String path = req.getServletContext().getRealPath("Images/cover");
                //路径上添加上文件名
                String filePath = path+"/"+fileName;
                //数据库中的路径
                String dbPath = "Images/cover/"+fileName;
                book.setPic(dbPath);
                //保存文件,此时的item是文件
                item.write(new File(filePath));
            }
        }
        //将信息保存到数据库中
        int count = bookBiz.add(book);
        if(count>0) {
            //不想TypeServlet一样有application，这里需要先跳转到分页显示整理好数据后在跳转到book_list.jsp
            out.println("<script>alert('添加书籍成功');location.href='book.let?type=query&pageIndex=1';</script>");
        }
        else{
            out.println("<script>alert('添加书籍失败');location.href='book_add.jsp';</script>");
        }
    }
}
