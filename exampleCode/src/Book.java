import java.lang.*;

class Book{
  private String name;
  private String author;
  private int publishYear;
  private String comment;

  public Book()
  {
    super();
  }

  public Book(String name, String author, int publishYear, String comment)
  {
    this.name = name;
    this.author = author;
    this.publishYear = publishYear;
    this.comment = comment;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  public void setAuthor(String author)
  {
    this.author = author;
  }
  public void setPublishYear(int publishYear)
  {
    this.publishYear = publishYear;
  }
  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public String getName()
  {
    return name;
  }
  public String getAuthor()
  {
    return author;
  }
  public int getPublishYear()
  {
    return publishYear;
  }
  public String getComment()
  {
    return comment;
  }
}
