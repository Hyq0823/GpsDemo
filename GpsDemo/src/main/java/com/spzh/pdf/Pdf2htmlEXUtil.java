package  com.spzh.pdf;

public class Pdf2htmlEXUtil {
private static final int OS = 1;
    /**
     * 调用pdf2htmlEX将pdf文件转换为html文件
     * @param exeFilePath pdf2htmlEX.exe文件路径
     * @param pdfFile pdf文件绝对路径
     * @param [] 生成的html文件存放路径
     * @param htmlName 生成的html文件名称
     * @return
     */
    public static boolean pdf2html(String exeFilePath,String pdfFile,String destDir,String htmlFileName){
        if(OS==2){//linux
            return pdf2html_linux(htmlFileName, htmlFileName, htmlFileName);
        }
        if(!(exeFilePath!=null&&!"".equals(exeFilePath)
                &&pdfFile!=null&&!"".equals(pdfFile)
                &&htmlFileName!=null&&!"".equals(htmlFileName))){
            System.out.println("传递的参数有误！");
            return false;
        }
        Runtime rt = Runtime.getRuntime();
        StringBuilder command = new StringBuilder();
        command.append(exeFilePath).append(" ");

        java.io.File fdir = new java.io.File(destDir);
        if(!fdir.exists()){
            System.out.println("文件夹不存在...");
        }

        if(destDir!=null&&!"".equals(destDir.trim()))//生成文件存放位置,需要替换文件路径中的空格
            command.append("--dest-dir ").append(destDir.replace(" ", "\" \"")).append(" ");
        command.append("--optimize-text 1 ");//尽量减少用于文本的HTML元素的数目 (default: 0)
        command.append("--zoom 1.4 ");
        command.append("--process-outline 0 ");//html中显示链接：0——false，1——true
        command.append("--font-format woff ");//嵌入html中的字体后缀(default ttf) ttf,otf,woff,svg
        command.append(pdfFile.replace(" ", "\" \"")).append(" ");//需要替换文件路径中的空格
        if(htmlFileName!=null&&!"".equals(htmlFileName.trim())){
            command.append(htmlFileName);
            if(htmlFileName.indexOf(".html")==-1)
                command.append(".html");
        }
        try {
            System.out.println("Command："+command.toString());
            Process p = rt.exec(command.toString());
            StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
            //开启屏幕标准错误流
            errorGobbler.start();
            StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), "STDOUT");
            //开启屏幕标准输出流
            outGobbler.start();
            int w = p.waitFor();
            int v = p.exitValue();
            if(w==0&&v==0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean pdf2html_linux(String pdfFile,String destDir,String htmlFileName){
        if(!(pdfFile!=null&&!"".equals(pdfFile)
                &&htmlFileName!=null&&!"".equals(htmlFileName))){
            System.out.println("传递的参数有误！");
            return false;
        }
        Runtime rt = Runtime.getRuntime();
        StringBuilder command = new StringBuilder();
        command.append("pdf2htmlEX").append(" ");
        if(destDir!=null&&!"".equals(destDir.trim()))//生成文件存放位置,需要替换文件路径中的空格
            command.append("--dest-dir ").append(destDir.replace(" ", "\" \"")).append(" ");
        command.append("--optimize-text 1 ");//尽量减少用于文本的HTML元素的数目 (default: 0)
        command.append("--process-outline 0 ");//html中显示链接：0——false，1——true
        command.append("--font-format woff ");//嵌入html中的字体后缀(default ttf) ttf,otf,woff,svg
        command.append(pdfFile.replace(" ", "\" \"")).append(" ");//需要替换文件路径中的空格
        if(htmlFileName!=null&&!"".equals(htmlFileName.trim())){
            command.append(htmlFileName);
            if(htmlFileName.indexOf(".html")==-1)
                command.append(".html");
        }
        try {
            System.out.println("Command："+command.toString());
            Process p = rt.exec(command.toString());
            StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
            //开启屏幕标准错误流
            errorGobbler.start();
            StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), "STDOUT");
            //开启屏幕标准输出流
            outGobbler.start();
            int w = p.waitFor();
            int v = p.exitValue();
            if(w==0&&v==0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args){
        pdf2html("C:\\Users\\hyq\\Downloads\\pdf2htmlEX-0.12-win32-static-with-poppler-data\\pdf2htmlEX.exe",
                //"C:\\Users\\hyq\\Desktop\\hyq.git\\GpsDemo\\GpsDemo\\web\\static\\pdf\\netty.pdf",
                "C:\\Users\\hyq\\Documents\\Tencent Files\\823547749\\FileRecv\\2.pdf",
                "C:\\Users\\hyq\\Desktop\\hyq.git\\GpsDemo\\GpsDemo\\web\\static\\pdf\\html"
                ,"222.html");
    }
}
