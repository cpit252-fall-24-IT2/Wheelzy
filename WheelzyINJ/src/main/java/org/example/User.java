package org.example;
// in Development... =)
public class User implements Observer {
    private String username;
    private String password;
    private String phoneNumber;


    private String email;

    private String address;


    public User(String username, String password, String phoneNumber, String email, String address) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }


    public String getAddress() {
        return address;
    }

    public String toFile() {
        return username + "," + password + "," + phoneNumber + "," + email + "," + address;
    }


    @Override
    public void update(String message) {
        System.out.println("Notification for " + username + ": " + message);
    }
}


//    public static class Builder {
//        private String username;
//        private String password;
//        private String phoneNumber;
//        private String role;
//
//        private String email;
//        private String job;
//        private String address;
//
//
//        public Builder setUsername(String username) {
//            if(username == null )
//                throw new IllegalArgumentException(" A username is required !");
//            this.username = username;
//            return this;
//        }
//
//        public Builder setPassword(String password) {
//            if(password == null )
//                throw new IllegalArgumentException(" A password is required !");
//            this.password = password;
//            return this;
//        }
//
//        public Builder setPhoneNumber(String phoneNumber) {
//            if(phoneNumber == null )
//                throw new IllegalArgumentException(" A phoneNumber is required !");
//            this.phoneNumber = phoneNumber;
//            return this;
//        }
//
//        public Builder setRole(String role) {
//            if(role == null )
//                throw new IllegalArgumentException(" A role is required !");
//            this.role = role;
//            return this;
//        }
//
//        public Builder setEmail(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public Builder setJob(String job) {
//            this.job = job;
//            return this;
//        }
//
//        public Builder setAddress(String address) {
//            this.address = address;
//            return this;
//        }
//
//        public User build() {
//            return new User(this);
//        }
//    }
//}

