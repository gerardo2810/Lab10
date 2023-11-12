package com.example.webapphr1_2023.Beans;

public class Department {

    private int departmentId;
    private String departmentName;
    private Employee managerId;
    private Location locationId;

    /**
     * @return the departmentId
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the managerId
     */
    public Employee getManagerId(){
        return managerId;
    }
    public  void setManagerId(Employee managerId){
        this.managerId=managerId;
    }

    public Location getLocationId(){
        return locationId;
    }

    public void setLocationId(Location locationId){
        this.locationId=locationId;
    }
}
