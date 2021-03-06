package com.htkj.cfdScenic.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.utils.GenerateSequenceUtil;
import com.htrj.common.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/img")
public class ImageController extends BaseController {
	
	public static PropertiesLoader appPropertiesLoader = new PropertiesLoader("application.properties");
	public static final String imagePath = appPropertiesLoader.getProperty("imagePath");
	public static final String requestImgPath = appPropertiesLoader.getProperty("requestImgPath");
	
	@Autowired
	private ShopInformationService consumerUserService;
	
	
	@ResponseBody
	@RequestMapping("/getImg")
	public void getImages(String imageUrl){
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		FileInputStream fis = null;
		webContext.getResponse().setContentType("image/gif");//返回格式是图片
		try {
	        OutputStream out = webContext.getResponse().getOutputStream();
	        String newAddress = imagePath+userId;
	        File file = new File(newAddress + imageUrl);
	        fis = new FileInputStream(file);
	        byte[] b = new byte[fis.available()];//fis.available() 文件的大小
	        fis.read(b);
	        out.write(b);
	        out.flush();
	    } catch (Exception e) {
	         e.printStackTrace();
	    } finally {
	        if (fis != null) {
	            try {
	               fis.close();
	            } catch (IOException e) {
	            e.printStackTrace();
	        }   
	          }
	    }
	}
	 
    @RequestMapping("/upload")
    @ResponseBody
    public String addUser(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request){  
    	ResponseMsg msg = new ResponseMsg();
    	String json = new String();
    	String token = webContext.getRequest().getHeader("Authorization");
		Long userId = consumerUserService.getUserIdByToken(token);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		//测试数据
		//token="";
		//userId= 1l;
		if(token!=null){
			if(userId!=null){
				try {
					for(int i = 0;i<files.length;i++){  
						System.out.println("fileName---------->" + files[i].getOriginalFilename());  
						String newAddress = imagePath+userId;
						if(!files[i].isEmpty()){  
							int pre = (int) System.currentTimeMillis();  
							File file1 = new File(newAddress);
							//如果文件夹不存在则创建    
							if(!file1.exists()  && !file1.isDirectory()){       
								file1.mkdir();    
							}
                            String[] split = files[i].getOriginalFilename().split("\\.");
                            String name = split[split.length-1];
                            //时间戳+文件名
                            String newName = new Date().getTime() + GenerateSequenceUtil.getUniqueId() +"."+ name;
							//上传的地址
							String imgrul = newAddress+ "\\" + newName;
							//存数据的地址
							String imageURL = requestImgPath + userId+"/"+newName;
							//拿到输出流，同时重命名上传的文件  
							FileOutputStream os = new FileOutputStream(imgrul);  
							//拿到上传文件的输入流  
							FileInputStream in = (FileInputStream) files[i].getInputStream();  
							//以写字节的方式写文件  
							int b = 0;  
							while((b=in.read()) != -1){  
								os.write(b);  
							}  
							os.flush();  
							os.close();  
							in.close();  
							int finaltime = (int) System.currentTimeMillis();  
							System.out.println(finaltime - pre);  
							//存名字
							//list.get(i).put("imgURL", imgrul);
							Map<String,String> map=new HashMap<String,String>();
							map.put("imageURL"+i, imageURL);
							list.add(map);
						}
					}
					msg.setHearder(0, "success");
					msg.setData(list);
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
        json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
    }

    @ResponseBody
    @RequestMapping("/getWebImg")
    public void getWebImg(String imageUrl){

        FileInputStream fis = null;
        webContext.getResponse().setContentType("image/gif");//返回格式是图片
        try {
            OutputStream out = webContext.getResponse().getOutputStream();

            String paramValue = imageUrl;  
        	paramValue = new String(paramValue.trim().getBytes("ISO-8859-1"), "UTF-8");


            File file = new File(imagePath + paramValue);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];//fis.available() 文件的大小
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping("/webUpload")
    @ResponseBody
    public String webUpload(@RequestParam("imageFile") CommonsMultipartFile[] files,HttpServletRequest request){
        ResponseMsg msg = new ResponseMsg();
        String json = new String();
        /*String token = webContext.getRequest().getHeader("Authorization");
        Long userId = consumerUserService.getUserIdByToken(token);*/
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        //测试数据
        //token="";
        //userId= 1l;
       /* if(token!=null){
            if(userId!=null){*/
                try {
                    for(int i = 0;i<files.length;i++){
                        System.out.println("fileName---------->" + files[i].getOriginalFilename());
                        String newAddress = imagePath;
                        if(!files[i].isEmpty()){
                            int pre = (int) System.currentTimeMillis();
                            File file1 = new File(newAddress);
                            //如果文件夹不存在则创建
                            if(!file1.exists()  && !file1.isDirectory()){
                                file1.mkdir();
                            }
                            String[] split = files[i].getOriginalFilename().split("\\.");
                            String name = split[split.length-1];
                            //时间戳+文件名
                            String newName = new Date().getTime() + GenerateSequenceUtil.getUniqueId() +"."+ name;
                            //上传的地址
                            String imgrul = newAddress+ "\\" + newName;
                            //存数据的地址
                            String imageURL = requestImgPath +newName;
                            //拿到输出流，同时重命名上传的文件
                            FileOutputStream os = new FileOutputStream(imgrul);
                            //拿到上传文件的输入流
                            FileInputStream in = (FileInputStream) files[i].getInputStream();
                            //以写字节的方式写文件
                            int b = 0;
                            while((b=in.read()) != -1){
                                os.write(b);
                            }
                            os.flush();
                            os.close();
                            in.close();
                            int finaltime = (int) System.currentTimeMillis();
                            System.out.println(finaltime - pre);
                            //存名字
                            //list.get(i).put("imgURL", imgrul);
                            Map<String,String> map=new HashMap<String,String>();
                            map.put("imageURL"+i, imageURL);
                            list.add(map);
                        }
                    }
                    msg.setHearder(0, "success");
                    msg.setData(list);
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.setHearder(1, "error");
                }
           /* }else{
                msg.setHearder(3, "异地登入!");
            }
        }else{
            msg.setHearder(2, "认证失败,请重新登入!");
        }*/
        json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
        System.out.println(json);
        return json;
    }
	
	
	 @RequestMapping("/upload2")  
	    public String upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
	        //创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            while(iter.hasNext()){  
	                //记录上传过程起始时的时间，用来计算上传时间  
	                int pre = (int) System.currentTimeMillis();  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	                        System.out.println(myFileName);  
	                        //重命名上传后的文件名  
	                        String fileName = "demoUpload" + file.getOriginalFilename();  
	                        //定义上传路径  
	                        String path = "E:/" + fileName;  
	                        File localFile = new File(path);  
	                        file.transferTo(localFile);  
	                    }  
	                }  
	                //记录上传该文件后的时间  
	                int finaltime = (int) System.currentTimeMillis();  
	                System.out.println(finaltime - pre);  
	            }  
	              
	        }  
	        return "/success";  
	    } 
	 @RequestMapping("/updateUeditor")  
	 public void updateUeditor(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
	        //创建一个通用的多部分解析器  
	        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	        //判断 request 是否有文件上传,即多部分请求  
	        if(multipartResolver.isMultipart(request)){  
	            //转换成多部分request    
	            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	            //取得request中的所有文件名  
	            Iterator<String> iter = multiRequest.getFileNames();  
	            List list = new ArrayList();
	            while(iter.hasNext()){  
	                //记录上传过程起始时的时间，用来计算上传时间  
	                int pre = (int) System.currentTimeMillis();  
	                //取得上传文件  
	                MultipartFile file = multiRequest.getFile(iter.next());  
	                if(file != null){  
	                    //取得当前上传文件的文件名称  
	                    String myFileName = file.getOriginalFilename();  
	                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                    if(myFileName.trim() !=""){  
	                        System.out.println(myFileName);  
	                        //重命名上传后的文件名  
	                        String fileName = file.getOriginalFilename();  
	                        //定义上传路径  
	                        String path = "E:/" + fileName;  
	                        File localFile = new File(path);  
	                        file.transferTo(localFile);
	                        list.add(path);
	                    }  
	                }  
	                //记录上传该文件后的时间  
	                int finaltime = (int) System.currentTimeMillis();  
	                System.out.println(finaltime - pre);  
	            }  
	              
	        }  
	    } 
}
