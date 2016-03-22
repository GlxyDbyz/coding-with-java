package org.dbyz.frameworks.quartz;  
  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
import org.quartz.Job;  
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
  
public class MyJob implements Job {  
    SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    public void execute(JobExecutionContext arg0) throws JobExecutionException {  
        System.out.println(DateFormat.format(new Date()) + "running ....");  
    }  
  
}  