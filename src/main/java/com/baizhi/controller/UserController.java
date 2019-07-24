package com.baizhi.controller;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dto.DTO;
import com.baizhi.entity.Guru;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDTO;
import com.baizhi.entity.UserRegist;
import com.baizhi.service.UserService;
import com.google.gson.Gson;
import io.goeasy.GoEasy;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService ;

    public void setUserService(UserService userService) { this.userService = userService; }




    @ResponseBody
    @RequestMapping("/findByPage")
    public DTO findByPage(Integer page, Integer rows){
        DTO<User> userDTO = userService.queryByPage(page, rows);
        return userDTO ;
    }
    @ResponseBody
    @RequestMapping("/ope")
    public void ope(String[] id, String oper, User user){
        if(oper.equals("del")){
            for (String s : id) {
                userService.remove(s);
            }
        }else if(oper.equals("edit")){
            userService.update(user);
        }else{
           userService.add(user);
        }
    }
    @ResponseBody
    @RequestMapping("/updateStatus")
    public void updateStatus(String id, String status){
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        userService.update(user);
    }
    @RequestMapping("/outpoi")
    public String outpoi(String path){
       try{
           List<User> list = userService.queryAll();
           List<User> newlist = new ArrayList<>();
           for (User user : list) {
               String cover = user.getCover();
               String cover1 = "D:/ider/ider-program/cmfz_sj/src/main/webapp/carouselPic/"+cover;
               user.setCover(cover1);
               newlist.add(user);
           }
           Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","用户表"),
                   User.class, newlist);
           int i = path.lastIndexOf("/");
           String substring = path.substring(0,i+1);
           if(substring.equals("")){
               return "500" ;
           }
           File file = new File(substring);
           if(!file.exists()){
               file.mkdir();
           }
           workbook.write(new FileOutputStream(path));
           workbook.close();
           return "200" ;

       }catch(Exception e){
           e.printStackTrace();
           return "500";
       }
    }
    @RequestMapping("/inpoi")
    public String inpoi(MultipartFile file) throws  Exception{
        Workbook workbook = new HSSFWorkbook(file.getInputStream());
        //获取第一个表
        Sheet sheet = workbook.getSheetAt(0);
        //获取一共有多少行
        int lastRowNum = sheet.getLastRowNum();
        //创建一个集合去接受
        List<User> users = new ArrayList<>();
        for (int i = 0; i < lastRowNum-2; i++) {
            User user = new User();
            Row row = sheet.getRow(i+2);



            Cell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
            String id = cell.getStringCellValue();
            user.setId(id);
            Cell cell1 = row.getCell(1);
            cell1.setCellType(CellType.STRING);
            user.setPassword(cell1.getStringCellValue());


            Cell cell2 = row.getCell(2);
            cell2.setCellType(CellType.STRING);
            user.setSalt(cell2.getStringCellValue());


            Cell cell3 = row.getCell(3);
            cell3.setCellType(CellType.STRING);
            user.setPhone(cell3.getStringCellValue());

            Cell cell4 = row.getCell(4);
            cell4.setCellType(CellType.STRING);
            user.setDharmaName(cell4.getStringCellValue());


            Cell cell5 = row.getCell(5);
            cell5.setCellType(CellType.STRING);
            user.setProvince(cell5.getStringCellValue());


            Cell cell6 = row.getCell(6);
            cell6.setCellType(CellType.STRING);
            user.setCity(cell6.getStringCellValue());


            Cell cell7 = row.getCell(7);
            cell7.setCellType(CellType.STRING);
            user.setGender(cell7.getStringCellValue());
            Cell cell9 = row.getCell(8);
            cell9.setCellType(CellType.STRING);
            user.setPersonalSign(cell9.getStringCellValue());
            Cell cell10 = row.getCell(9);
            cell10.setCellType(CellType.STRING);
            user.setProfile(cell10.getStringCellValue());
            Cell cell11 = row.getCell(10);
            cell11.setCellType(CellType.STRING);
            user.setStatus(cell11.getStringCellValue());
            Cell cell12 = row.getCell(11);
            cell12.setCellType(CellType.STRING);
            user.setCover(cell12.getStringCellValue());
            Cell cell13 = row.getCell(12);
            cell13.setCellType(CellType.STRING);
            String stringCellValue = cell13.getStringCellValue();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(stringCellValue);
            user.setRegistTime(parse);
            Cell cell14 = row.getCell(13);
            cell14.setCellType(CellType.STRING);
            Guru guru = new Guru();
            guru.setId(cell14.getStringCellValue());
            user.setGuru(guru);
            users.add(user);
        }
        for (User user : users) {
            System.out.println(user);
        }
        for (int i = 0 ; i<users.size(); i++) {
            String s = userService.queryById(users.get(i).getId());
            System.out.println(s);
            if(s != null){
                System.out.println("===========");
                users.remove(i);
                i = i-1 ;
            }
        }
        if(!users.isEmpty()){
            for (User user : users) {
                userService.add(user);
            }
        }
        return "forward:/back/main.jsp?sign=1";
    }
    @ResponseBody
    @RequestMapping("/outpoixz")
    public void outpoixz(HttpServletRequest request, HttpServletResponse response ) throws  Exception{
        List<User> list1 = userService.queryAll();
        List<User> newlist = new ArrayList<>();
        for (User user : list1) {
            String cover = user.getCover();
            String cover1 = "D:/ider/ider-program/cmfz_sj/src/main/webapp/carouselPic/"+cover;
            user.setCover(cover1);
            newlist.add(user);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息","用户表"),
                User.class, newlist);
        workbook.write(new FileOutputStream("D:/ider/ider-program/cmfz_sj/src/main/webapp/poi/user.xls"));
        workbook.close();
        String path = request.getSession().getServletContext().getRealPath("/poi");
        //获取当前文件
        File file = new File(path,"user.xls");
        //设置响应类型
        response.setContentType(request.getSession().getServletContext().getMimeType(".xls"));
        //设置响应头
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("user.xls","UTF-8"));
        //将文件响应到浏览器
        FileCopyUtils.copy(new FileInputStream(file),response.getOutputStream());
    }
    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Object> login(User user){
            Map<String, Object> map = userService.queryByNameAndCode(user);
            return map ;
    }
    @RequestMapping("/userfenbu")
    @ResponseBody
    public void userfenbu(){
        List<List<UserDTO>> list = new ArrayList<>();
        List<UserDTO> nanlist = userService.queryMap();
        List<UserDTO> nvlist = new ArrayList<>();
        for(int i = 0 ; i< nanlist.size(); i++){
            if(nanlist.get(i).getGender().equals("女")){
                nvlist.add(nanlist.get(i));
                nanlist.remove(nanlist.get(i));
                i = i-1 ;
            }
        }
        list.add(nanlist);
        list.add(nvlist);
        Gson gson = new Gson();
        String s = gson.toJson(list);
        GoEasy goEasy = new GoEasy( "https://rest-hangzhou.goeasy.io", "BC-bb534c8ed9c343f18b8776e3ce18f78b");
                goEasy.publish("my_channel",s);
    }
    @RequestMapping("/userr")
    @ResponseBody
    public void userRegist(){
        List<UserRegist> userDTO1s = userService.queryRegist();
        List<String> timelist = new ArrayList<>();
        List<String> numberlist = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        for (UserRegist userDTO1 : userDTO1s) {
            timelist.add(userDTO1.getRegistTime());
            numberlist.add(userDTO1.getNumber());
        }
        list.add(timelist);
        list.add(numberlist);
        Gson gson = new Gson();
        String s = gson.toJson(list);
        GoEasy goEasy = new GoEasy( "https://rest-hangzhou.goeasy.io", "BC-bb534c8ed9c343f18b8776e3ce18f78b");
        goEasy.publish("my",s);
        Map map = new HashMap();
    }



    @RequestMapping("/code")
    @ResponseBody
    public String registcode(String phone){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIREyDfinNnlQc", "uWidxZRMKov5e5zzw9CbWv24Yb9s4S");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "小蓝龟");
        request.putQueryParameter("TemplateCode", "SMS_171117633");
        Random r = new Random();
        String codenum = "" ;
        for(int i = 0 ; i< 6 ; i++){
            int i1 = r.nextInt(10);
            codenum+= i1 ;
        }
        request.putQueryParameter("TemplateParam", "{'code':'"+codenum+"'}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return codenum ;
    }
    @RequestMapping("/regist")
    @ResponseBody
    public void regist(HttpServletRequest request,User user,MultipartFile file) throws IOException {
        String originalFilename = "" ;
        String realPath = request.getSession().getServletContext().getRealPath("/carouselPic");
        if(file != null ){
           originalFilename = file.getOriginalFilename();
            file.transferTo(new File(realPath,originalFilename));
        }else{
            originalFilename = "timg.jpg" ;
        }
        File file1 = new File(realPath);
        if(!file1.exists()){
            file1.mkdir();
        }
        String codenum = "" ;
        Random r = new Random();
        for(int i = 0 ; i< 4 ; i++){
            int i1 = r.nextInt(10);
            codenum+= i1 ;
        }
        String realPasswrod = user.getPassword();
        String MD5Password = DigestUtils.md5Hex(codenum+realPasswrod+codenum);
        user.setPassword(MD5Password);
        user.setSalt(codenum);
        user.setId(UUID.randomUUID().toString());
        user.setStatus("正常");
        user.setCover(originalFilename);
        user.setRegistTime(new Date());
        userService.add(user);
        userRegist();
        userfenbu() ;
    }

    @RequestMapping("/testPhone")
    @ResponseBody
    public String testPhone(String phone){

        User user = userService.queryByPhone(phone);
        System.out.println(user);
        if(user != null){
            return "no" ;
        }else{
            return "ok";
        }
    }


}
