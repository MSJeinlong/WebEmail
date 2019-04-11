package bean;

/**
 * user-email-receiveEmails
 * 即表示某个用户绑定的某个邮箱的所有收件箱邮件
 */
public class ReceivedEmail {
    private int emailId;            //邮件编号（标识符）
    private String userName;        //用户名
    private int emailNumber;         //邮件在收件箱中的序号
    private String emailAddress;     //收件箱的邮件地址
    private String subject;         //邮件主题
    private String sender;          //发件人
    private String receiverTO;      //收件人地址
    private String receiverCC;      //抄送人地址
    private String sentDate;        //接收时间
    private boolean isSeen;          //邮件是否已读
    private String priority;       //邮件优先级
    private boolean isReplySign;     //邮件是否需要回执
    private int emailSize;        //邮件大小
    private boolean haveAttachment;      //是否包含附件
    private String attachmentPath;         //附件在本地计算机中保存的路径
    private String attachmentName;
    private int attachmentSize;        //附件大小
    //  private String fileName;        //附件名称
    private String content;         //邮件正文

    @Override
    public String toString() {
        return "ReceivedEmail{" +
                "emailId=" + emailId +
                ", userName='" + userName + '\'' +
                ", emailNumber=" + emailNumber +
                ", emailAddress='" + emailAddress + '\'' +
                ", subject='" + subject + '\'' +
                ", sender='" + sender + '\'' +
                ", receiverTO='" + receiverTO + '\'' +
                ", receiverCC='" + receiverCC + '\'' +
                ", sentDate='" + sentDate + '\'' +
                ", isSeen=" + isSeen +
                ", priority='" + priority + '\'' +
                ", isReplySign=" + isReplySign +
                ", emailSize=" + emailSize +
                ", haveAttachment=" + haveAttachment +
                ", attachmentPath='" + attachmentPath + '\'' +
                ", attachmentSize=" + attachmentSize +
                ", content='" + content + '\'' +
                ", isFavorites=" + isFavorites +
                ", isDelete=" + isDelete +
                '}';
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    private boolean isFavorites;     //邮件是否被收藏
    private boolean isDelete;        //邮件是否暂时删除（逻辑上的删除，其实是移动到“已删除”）

    public ReceivedEmail() {
    }

    public int getEmailNumber() {
        return emailNumber;
    }

    public void setEmailNumber(int emailNumber) {
        this.emailNumber = emailNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiverTO() {
        return receiverTO;
    }

    public void setReceiverTO(String receiverTO) {
        this.receiverTO = receiverTO;
    }

    public String getReceiverCC() {
        return receiverCC;
    }

    public void setReceiverCC(String receiverCC) {
        this.receiverCC = receiverCC;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isReplySign() {
        return isReplySign;
    }

    public void setReplySign(boolean replySign) {
        isReplySign = replySign;
    }

    public int getEmailSize() {
        return emailSize;
    }

    public void setEmailSize(int emailSize) {
        this.emailSize = emailSize;
    }

    public boolean isHaveAttachment() {
        return haveAttachment;
    }

    public void setHaveAttachment(boolean haveAttachment) {
        this.haveAttachment = haveAttachment;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public int getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(int attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorites() {
        return isFavorites;
    }

    public void setFavorites(boolean favorites) {
        isFavorites = favorites;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
