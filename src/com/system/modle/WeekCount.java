    package com.system.modle;

    import java.util.Date;

    public class WeekCount {
        private String classid;
        private String classname;
        private String weekcount;
        private String weekcountid;
        private String studentid;
        private String studentname;
        private String signinsituation;
        private String signoutsituation;


        public WeekCount(String classid, String classname, String weekcount, String weekcountid, String studentid, String studentname, String signinsituation, String signoutsituation) {
            this.classid = classid;
            this.classname = classname;
            this.weekcount = weekcount;
            this.weekcountid = weekcountid;
            this.studentid = studentid;
            this.studentname = studentname;
            this.signinsituation = signinsituation;
            this.signoutsituation = signoutsituation;
        }

        public String getClassid() {
            return classid;
        }

        public void setClassid(String classid) {
            this.classid = classid;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getWeekcount() {
            return weekcount;
        }

        public void setWeekcount(String weekcount) {
            this.weekcount = weekcount;
        }

        public String getWeekcountid() {
            return weekcountid;
        }

        public void setWeekcountid(String weekcountid) {
            this.weekcountid = weekcountid;
        }

        public String getStudentid() {
            return studentid;
        }

        public void setStudentid(String studentid) {
            this.studentid = studentid;
        }

        public String getStudentname() {
            return studentname;
        }

        public void setStudentname(String studentname) {
            this.studentname = studentname;
        }

        public String getSigninsituation() {
            return signinsituation;
        }

        public void setSigninsituation(String signinsituation) {
            this.signinsituation = signinsituation;
        }

        public String getSignoutsituation() {
            return signoutsituation;
        }

        public void setSignoutsituation(String signoutsituation) {
            this.signoutsituation = signoutsituation;
        }

        @Override
        public String toString() {
            return "WeekCount{" +
                    "classid='" + classid + '\'' +
                    ", classname='" + classname + '\'' +
                    ", weekcount='" + weekcount + '\'' +
                    ", weekcountid='" + weekcountid + '\'' +
                    ", studentid='" + studentid + '\'' +
                    ", studentname='" + studentname + '\'' +
                    ", signinsituation='" + signinsituation + '\'' +
                    ", signoutsituation='" + signoutsituation + '\'' +
                    '}';
        }
    }