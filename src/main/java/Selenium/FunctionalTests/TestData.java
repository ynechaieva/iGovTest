package Selenium.FunctionalTests;

public class TestData {
    private String login;
    private String password;
    private String SMSCode;
    private String email;

    private TestData(){};

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public String getSMSCOde(){
        return SMSCode;
    }

    public String getEmail(){return email;}

    public static Builder newBuilder(){
        return new TestData().new Builder();
    }

    public class Builder {
        private Builder(){};

        public Builder setLogin(String login){
            TestData.this.login = login;
            return this;
        }

        public Builder setPassword(String password){
            TestData.this.password = password;
            return this;
        }

        public Builder setSMSCode(String SMSCode){
            TestData.this.SMSCode = SMSCode;
            return this;
        }

        public Builder setEmail(String email){
            TestData.this.email = email;
            return this;
        }

        public TestData build(){
            return TestData.this;
        }
    }
}
