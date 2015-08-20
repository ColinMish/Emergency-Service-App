package com.example.colin.emergencyserviceapp;

/**
 * Created by colin on 20/08/15.
 */
public class SMSObject {

    private static int service;
    private static String location;
    private static String description;
    private static int phoneNumber;

    public SMSObject() {

    }

    public static void setService( int newService )
    {
        service = newService;
    }

    public static int getService()
    {
        return service;
    }

    public static void setLocation( String newLocation )
    {
        location = newLocation;
    }

    public static String getLocation()
    {
        return location;
    }

    public static void setDescription( String newDescription)
    {
        description = newDescription;
    }

    public static String getDescription()
    {
        return description;
    }

    public static void setPhoneNumber( int newPhoneNumber )
    {
        phoneNumber = newPhoneNumber;
    }

    public static int getPhoneNumber()
    {
        return phoneNumber;
    }
}
