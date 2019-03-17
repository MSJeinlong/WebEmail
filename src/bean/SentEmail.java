package bean;

/**
 * user-email-sentEmail
 * 即某个用户所绑定邮箱的已经发送的邮件
 */
public class SentEmail {
    private int emailId;            //邮件编号
    private String userName;        //用户名
    private String emailFrom;       //发件人
    private String emailTO;         //收件人
    private String emailCC;         //抄送人
    private String emailBC;         //暗送人
    private String subject;         //主题
    private String content;         //邮件正文
    private String haveAttachment;  //是否包含附件
    private String attachmentPath;      //附件路径
    private String attachmentName;      //附件名称
    private String attachmentSize;      //附件大小
    private boolean favorites;          //是否已经收藏
    private boolean delete;             //是否删除了

    public SentEmail() {
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTO() {
        return emailTO;
    }

    public void setEmailTO(String emailTO) {
        this.emailTO = emailTO;
    }

    public String getEmailCC() {
        return emailCC;
    }

    public void setEmailCC(String emailCC) {
        this.emailCC = emailCC;
    }

    public String getEmailBC() {
        return emailBC;
    }

    public void setEmailBC(String emailBC) {
        this.emailBC = emailBC;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHaveAttachment() {
        return haveAttachment;
    }

    public void setHaveAttachment(String haveAttachment) {
        this.haveAttachment = haveAttachment;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(String attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public boolean isFavorites() {
        return favorites;
    }

    public void setFavorites(boolean favorites) {
        this.favorites = favorites;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "SentEmail{" +
                "emailId=" + emailId +
                ", userName='" + userName + '\'' +
                ", emailFrom='" + emailFrom + '\'' +
                ", emailTO='" + emailTO + '\'' +
                ", emailCC='" + emailCC + '\'' +
                ", emailBC='" + emailBC + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", haveAttachment='" + haveAttachment + '\'' +
                ", attachmentPath='" + attachmentPath + '\'' +
                ", attachmentName='" + attachmentName + '\'' +
                ", attachmentSize='" + attachmentSize + '\'' +
                ", favorites=" + favorites +
                ", delete=" + delete +
                '}';
    }
}
