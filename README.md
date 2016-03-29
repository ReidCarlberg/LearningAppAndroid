![Marketing Cloud](imgReadMe/marketing_cloud_logo.png)

# README

>Marketing Cloud Learning Apps are free to use, but are not official Salesforce.com Marketing Cloud products, and should be considered community projects - these apps are not officially tested or documented. For help on any Marketing Cloud Learning App please consult the Salesforce message boards or the issues section of this repository - Salesforce.com Marketing Cloud support is not available for these applications.

1. [About](#0001)

    1. [Marketing Cloud App Center](#0002)

    2. [Push Notifications](#0003)

    3. [Subscriber key](#0004)

    4. [Tags](#0005)

    5. [Beacon and Geofence Messages](#0006)

    6. [Analytics](#0006b)

2. [Implementation on Android](#0007)

    1. [Previous steps](#0008)

        1. [Provision Apps with Google](#0009)

        2. [Create your apps in the App Center](#0010)

            1. [Add app to App Center](#0011)

            2. [Integrate App Center app](#0012)
    2. [Setting up the SDK](#0013)

    2. [Implementing the SDK Push Notifications](#0014)

    3. [Subscriber Key Implementation](#0015)

    4. [Tag Implementation](#0016)

    5. [Beacon and Geofence Messages Implementation](#0017)

    6. [Implement Analytics in your Mobile App](#0018)

<a name="0001"></a>
# About

This project provides a template for creating a mobile app (Android or iOS) that uses the Journey Builder for Apps SDK.  It is also a UI for exploring its features and provides a mechanism to collect and send debugging information to learn about the workings of the SDK as you explore.

The code in this repository includes all of the code used to run the fully functional APK including an App ID and Access Token to let you test and debug the application. These keys will trigger an hourly automated push message of a timestamp, indicating that the application is properly setup. To create a new app the following keys must be set with your own values within the corresponding file.

**secrets.xml**

1. `app_id`: the App ID for your development app as defined in the App Center section of the Marketing Cloud.

2. `gcm_sender_id`: the Google Cloud Messaging ID as defined in the Google Cloud Developers Console for your app.

3. `access_token`: the Access Token for your development app as defined in the App Center section of the Marketing Cloud.

You can use different keys for the staging/testing phase and the production phase.  Staging/testing keys are indicated by the prefix `staging_`.

<a name="0002"></a>
## Marketing Cloud App Center

App Center is the central development console for using Fuel’s APIs and building Marketing Cloud apps.

In order to connect your app to the Marketing Cloud, you must first create a MobilePush app in the App Center. 

Each app in App Center represents an application connected to the Marketing Cloud. App Center currently manages four types of connected apps:

* *API Integration* allows you to leverage the Marketing Cloud APIs. Create an API Integration app when you want to use Fuel APIs to automate tasks or integrate business systems. API Integration apps utilize an OAuth2 client credentials flow to acquire access tokens directly from the Fuel authentication service.

* *Marketing Cloud apps* represent apps that live within the Salesforce Marketing Cloud and launch via the Marketing Cloud app menu. Marketing Cloud apps include custom apps built by your organization or apps installed from the Salesforce Marketing Cloud HubExchange. Marketing Cloud apps utilize a JSON Web Token (JWT) to acquire access tokens on behalf of logged in users.

* *Application Extensions* allow you to extend the Marketing Cloud with custom Journey Builder activities, Cloud Editor Blocks, and Automation Studio activities.

* *MobilePush apps* represent apps built for the iOS, Android, or Blackberry mobile platforms that use MobilePush to communicate with their users via push messages. The Salesforce Marketing Cloud classifies MobilePush apps as consumer-grade applications and utilize long-lived limited access tokens.

If you haven’t already, you should [create an App Center account](https://appcenter-auth.exacttargetapps.com/create).

If you have an App Center account, you can [log in to that account](https://appcenter-auth.exacttargetapps.com/redirect).
<a name="0003"></a>
## Push Notifications

MobilePush lets you create and send targeted push messages based on cross-channel consumer data to encourage app usage and deliver increased ROI.  With MobilePush, you view how users navigate through your app and because MobilePush is built on the Salesforce Marketing Cloud, you can easily integrate push message campaigns with any email, SMS, or social campaigns.
<a name="0004"></a>
## Subscriber key

A subscriber is a person who has opted to receive communications from your organization. 

A valid email address is required to receive emails and a phone number to receive SMS messages.  Additional information about subscribers can be tracked using profile and preference attributes.

The Subscriber Key serves to identify your subscribers.

It can be set to a specific value provided by the subscriber such as a phone number, email address, or other appropriate value but most importantly a value that you choose.

For example, using a subscriber key to identify a subscriber with a value other than the email address would allow you to:

* Maintain multiple sets of subscriber attributes for a single email address. For example, if a family shares an email address, you can use a subscriber key to uniquely identify each member of the family.

* Include a single email address multiple times on a list. For example, if a message interaction sends a separate message for each car a subscriber owns, it may be appropriate for a single subscriber to receive multiple messages.

The Salesforce Marketing Cloud interface as well as the Web Service SOAP API support functionality around subscribers identified with a subscriber key.
<a name="0005"></a>
## Tags

Tags let you implement contact segmentation. You can set tags for subscriptions as defined by user choice.  Additionally, use tags to collect information from the mobile app and for unstructured data or data that can contain many potential unknown values. For example, you can use tags when the number of potential attribute names exceeds the number of potential values of an individual attribute (such as the favorite brand specified by a contact).
<a name="0006"></a>
## Beacon and Geofence Messages

You can use the location capabilities of the *JB4A SDK* to target messages to a segmented group of contacts.  Send personalized messages to increase engagement.  The app pre-downloads geofence messages and triggers those messages when a mobile device crosses a geofence boundary.  To use this functionality:

1. The account must have access to both MobilePush and Location Services.

2. Ensure that you use version 7.8.0 or earlier of Google Play Services to enable geolocation for your app.

3. You must receive user permission to implement location services.

<a name="0006b"></a>
## Analytics

Mobile Application Analytics enables marketers to gather mobile app actions and behaviors from users and provides powerful visualizations of the data. The data helps you make informative decisions about how to structure your customer journeys, design your client facing experiences and tailor your digital marketing campaigns. The collected data is also available inside the Salesforce Marketing Cloud – ready to be used to segment messaging lists, provide highly personalized messaging content and drive 1:1  Custom Journeys.

After enabling the analytics feature in your app, visit the Web & Mobile Analytics application within the Marketing Cloud.

<a name="0007"></a>
# Implementation on Android
<a name="0008"></a>
## Previous steps

1. Provision Apps with Google  

2. Create your apps in the App Center

<a name="0009"></a>
### Provision Apps with Google

These steps are key to receiving push messages in your app.

Review the Android documentation regarding the integration of your Android mobile app with Google Cloud Messaging found in [Google Cloud Messaging (GCM) HTTP connection server.](https://developer.android.com/google/gcm/http.html)

1. Log into the [Google Developers Console](https://console.developers.google.com/) and click **Create a project...**.

    1. Enter a name for your project in the **PROJECT NAME** field.

    2. Use the suggested default ID for your project or click in **Edit** to enter a custom one.

    3. Click Create.

2. Record the Project Number value supplied by the Google Cloud Console. You will use this value later in your Android application code as the **Google Cloud Messaging Sender ID**.

    ![image alt text](imgReadMe/image_00.png)

3. In the the left menu click on **APIs** (**APIs & auth** section).

4. Enable **Google Cloud Messaging for Android** by clicking Google Cloud Messaging For Android:

    ![image alt text](imgReadMe/image_01.png)

5. Click the button that says "Enable API".

    ![image alt text](imgReadMe/image_02.png)

6. Click **Credentials** in the left menu.

7. Click **Add credentials** → **API key**, and select **Android key** in the dialog.

    ![image alt text](imgReadMe/image_03.png)

    ![image alt text](imgReadMe/image_04.png)

8. Retrieve the **SHA1 Certificate Fingerprint** of the Android Debug Key from a (Unix/Mac) Terminal or (Windows) Command Prompt.  *Have each developer on your team complete this step and provide their SHA1 Certificate Fingerprint for inclusion in the* ***allowed Android applications*** *field below*. ***You will also need to add an entry for your production signing key.***

    Unix/Mac: `keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore -list -v`

    Windows: `keytool -alias androiddebugkey -keystore %USERPROFILE%\.android\debug.keystore -list -v`

    ![image alt text](imgReadMe/image_05.jpg)

    ![image alt text](imgReadMe/image_06.jpg) 

9. Enter a name for your key, and click in **Add package name and fingerprint**. Enter your package name (must match the package name you will use in your Android project) and paste each developer’s **SHA1 Certificate Fingerprint** separated by a semicolon in the corresponding fields, and then click **Create**.

    ![image alt text](imgReadMe/image_07.png)

10. Click **Ok** in the dialog.

    ![image alt text](imgReadMe/image_08.png)

11. Now create a new Server key by repeating step 7 but choosing **Server key**.

12. Enter a name for the key. You can enter server IP address to filter request or leave this entry blank.

    *Important: Leaving this entry blank will simplify development but is not secure. When you have verified that you have things setup correctly you should restrict access by providing individual server IP addresses or, at least, restrict the range to your known address ranges.*

    ![image alt text](imgReadMe/image_09.png)

13. Click **Create** and copy the **API KEY** value from the **Server application**.

14. Use the API Key from the server application created above to add to your MobilePush app in the *Create your apps in the App Center* step. And use the project number to set the `gcm_sender_id` in your project.

<a name="0010"></a>
### Create your apps in the App Center

In order to connect your app to your Marketing Cloud account, you must follow these steps:

1. Add app to App Center.

2. Integrate the App Center app to your Marketing Cloud account.

3. Add the Provisioning info created in the GCM Console to the app in the App Center.

<a name="0011"></a>
#### Add app to App Center

To create a new MobilePush app:

1. [Log in to the App Center](https://appcenter-auth.exacttargetapps.com/redirect) ([create an account](https://appcenter-auth.exacttargetapps.com/create) if necessary).

2. Create a new app and select the MobilePush template.

    ![image alt text](imgReadMe/image_10.png)

3. Fill in, at a minimum, the mandatory fields in this form.

    ![image alt text](imgReadMe/image_11.png)

    *Depending on your setup, repeat this process if you plan on using different instances for production and development.*

    Note the following about the required fields:

      1. The **Name** can be anything you choose.

      2. The **Package** has no correlation to anything outside of the MarketingCloud ecosystem and can be **any** unique identifier for your application.

      3. The **Description** & **MobilePush Icon** fields are optional but will help you identify your application within your Marketing Cloud account. 

4. Click **Next** in order to integrate this new app with your Marketing Cloud account.

<a name="0012"></a>
#### Integrate App Center app

The MobilePush app created in the App Center must be connected to a specific Marketing Cloud account. You must have a login for your Marketing Cloud account in order to connect this MobilePush app to the correct Marketing Cloud account.

Follow these steps in order to connect this MobilePush app to the correct Marketing Cloud account:

1. Select an account (or New…) in the **Account** drop-down.

    ![image alt text](imgReadMe/image_12.png)

2. Select the **Production ExactTarget Account** button *unless otherwise instructed by your Salesforce Marketing Cloud relationship manager.*

3. Click **Link to Account**.

    A popup window (pictured below) will appear.

    ![image alt text](imgReadMe/image_13.png)

4. In an Enterprise 2.0 account, ensure that you select the correct business unit for your app integration.

5. Click **Integrate**.

6. In the GCM Client section, enter the server API KEY previously created in the [Provision Apps with Google](#0009) step (you can get this key by entering in the [Google Cloud Console](https://console.developers.google.com/)).

    ![image alt text](imgReadMe/image_14.png)

7. When you have all the fields required for your application’s platform(s) populated, click *Next*.

8. Review the information you provided and check for any potential errors and click **Finish**.

    You should be presented with a *Success!* message and an application details screen. Any of the areas can be edited by clicking the edit icon associated with the **Summary** or **Application Provisioning** sections.

    ![image alt text](imgReadMe/image_15.png)

Record the **Application ID** and the **Access Token** as they will be used later in the secrets.xml file.


<a name="0013"></a>
## Setting up the SDK

The `readyAimFire` method of the ETPush class configures the SDK to point to the correct code application.

Call this from your Application's Application.onCreate() method. This initializes ETPush.

When ReadyAimFire() is called for the first time for a device, it will get a device token
from Google and send to the MarketingCloud.

In ETPush.readyAimFire() you must set several parameters:

  * `app_id` and `access_token`: these values are taken from the Marketing Cloud definition for your app.

  * `gcm_sender_id` for the push notifications: this value is taken from the Google API console.

  * You can also set whether you enable location manager, cloud pages, and analytics.

To set the logging level, call ETPush.setLogLevel().

<a name="0014"></a>
## Push Notifications

Update the following files in your project:

1. secrets.xml
2. AndroidManifest.xml
3. build.gradle
4. app/build.gradle
5. ApplicationClass.java

**Secrets.xml**

The SDK can now be configured with the App ID and Access Token, as explained in the *About* section.  Update `app_id` and `access_token` with their respective values.

**AndroidManifest.xml**

In this file declare the following permissions:

*JB4A SDK Google Permissions* - These permissions are required to receive push messages which use the Google Cloud Messaging service.

[view the code](/app/src/main/AndroidManifest.xml#L5-L12)
```xml
<!-- JB4A SDK Google Permissions -->
<permission 
    android:name="${applicationId}.permission.C2D_MESSAGE"
    android:protectionLevel="signature" />

<uses-permission android:name="${applicationId}.permission.C2D_MESSAGE" />
<uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />
<!-- END JB4A SDK Google Permissions -->
```
*JB4A SDK required permissions* - These permissions are necessary for the SDK to function.  The first three permissions establish internet connection status for the application to synchronize with Marketing Cloud. The WAKE_LOCK permission allows PowerManager WakeLocks to keep the processor from sleeping or screen from dimming.

[view the code](/app/src/main/AndroidManifest.xml#L15-L20)
```xml
<!-- JB4A SDK required permissions -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.WAKE_LOCK" />
<!-- END JB4A SDK required permissions -->
```
In the activity section, make sure to include the ETPushReceiver and Service for the push notifications.

[view the code](/app/src/main/AndroidManifest.xml#L56-L84)
```xml
<!-- ETPushReceiver and Service -->
<receiver
    android:name="com.exacttarget.etpushsdk.ETPushReceiver"
    android:permission="com.google.android.c2dm.permission.SEND" >

   <intent-filter>
       <action android:name="${applicationId}.MESSAGE_OPENED" />
       <action android:name="com.exacttarget.etpushsdk.SEND_REGISTRATION" />
       <action android:name="com.google.android.c2dm.intent.RECEIVE" />
       <action android:name="com.google.android.c2dm.intent.REGISTRATION" />
       <action android:name="android.intent.action.ACTION_SHUTDOWN" />
       <action android:name="android.intent.action.AIRPLANE_MODE" />
       <action android:name="android.intent.action.BATTERY_LOW" />
       <action android:name="android.intent.action.BATTERY_OKAY" />
       <action android:name="android.intent.action.BOOT_COMPLETED" />
       <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
       <category android:name="${applicationId}" />
   </intent-filter>

   <intent-filter>
       <action android:name="android.intent.action.PACKAGE_REPLACED" />
       <data android:scheme="package" />
   </intent-filter>
</receiver>

<service
    android:name="com.exacttarget.etpushsdk.ETPushService"
    android:enabled="true" />

<!-- END ETPushReceiver and Service -->
```
**build.gradle**

Add the following repository:

[view the code](/build.gradle#L18-L26)
```gradle
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven {
            url "http://salesforce-marketingcloud.github.io/JB4A-SDK-Android/repository"
        }
    }
}
```
**app/build.gradle**

Include the following dependencies in your application's app/build.gradle file:

[view the code](/app/build.gradle#L33-L48)
```gradle
dependencies {
    /* Google's Support v4 for Notification compatibility */
    compile 'com.android.support:appcompat-v7:23.1.0'
    compile 'com.android.support:support-v4:23.1.0'
    compile 'com.android.support:design:23.1.0'

    /* SDK */
    compile 'com.exacttarget.etpushsdk:etsdk:4.0.6@aar'

    /* Google Play Services for Location and Google Cloud Messaging */
    compile 'com.google.android.gms:play-services-location:7.8.0'
    compile 'com.google.android.gms:play-services-gcm:7.8.0'

    /* 3rd Party Libraries Required for SDK integration */
    compile 'com.radiusnetworks:AndroidIBeaconLibrary:0.7.6'
}
```

**ApplicationClass.java**

The boolean parameters `ANALYTICS_ENABLED`, `CLOUD_PAGES_ENABLED`, `WAMA_ENABLED`, `LOCATION_ENABLED` and `PROXIMITY_ENABLED` enable certain functionalities of the SDK, however, they are not required for the push notifications themselves to function which will still be sent even if all are set to false.

<a name="0014"></a>
## Subscriber Key Implementation

1. Create a new activity called `SettingsActivity` that extends `PreferenceActivity` in your project.

2. Create a new fragment called `SettingsFragment` that extends `PreferenceFragment`.

3. Now create an instance of the SettingsFragment in the SettingsActivity class, add the following code to the `onCreate()` method:

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/SettingsActivity.java#L37-L38)
    ```java
    getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    ```
4. Create a new file called [preferences.xml](/app/src/main/res/xml/preferences.xml) in res/xml that will be the settings view.

5. Reference the preferences.xml file in the `onCreate()` method in the SettingsFragment class with the following code: `addPreferencesFromResource(R.xml.preferences);`

6. Add a private attribute SharedPreferences sp and set it as the default shared preference:

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/SettingsFragment.java#L50)
    ```java
    private SharedPreferences sp;
    …
    this.sp = getActivity().getPreferences(Context.MODE_PRIVATE);
    ```

7. Add a private attribute pusher, the instance of ETPush:

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/SettingsFragment.java#L54)
    ```java
    private ETPush pusher;
    …
    this.pusher = ETPush.getInstance();
    ```
8. Now create the reference to the EditTextPreference from preferences.xml and set the value stored in settings Preferences. Add an `OnPreferenceClickListener()` to open a Dialog with input for the user to enter their Subscriber Key.  This value is stored in the settings Preferences and will be passed to the pusher.

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/SettingsFragment.java#L94-L104)
    ```java
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(KEY_PREF_SUBSCRIBER_KEY, newSubscriberKey);
    editor.commit();
    …
    pusher.setSubscriberKey(newSubscriberKey);
    ```

It will take up to 15 minutes for the new value to be recorded in the Contact Record. If an internet connection is not available when the update is made, the SDK will save the update and send it whenever the network becomes available.

By default, if your app does not set the Subscriber Key using `setSubscriberKey()`, the registration sent will be matched with a Contact Record that matches the System Token included in the registration payload. If no match is found, then a new Subscriber Key will be set in the Marketing Cloud and will not be sent back to the SDK.

<a name="0016"></a>
## Tag Implementation

This feature is implemented in Settings Preferences.  We assume that the Subscriber Key feature has been implemented as described in this guide in order for the following steps to work.

1. Add a Set of tags as a private attribute.

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/SettingsFragment.java#L37)
    ```java
    private Set<String> allTags;
    ```

2. For the implementation of this feature, an instance of PreferenceScreen is needed to display the tags dynamically on the screen.
   
    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/SettingsFragment.java#L40)
    ```java
    private PreferenceScreen prefScreen;
    ```

3. In the onCreate() method set the values for prefScreen.

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/SettingsFragment.java#L53)
    ```java
    this.prefScreen = getPreferenceScreen();
    ```

4. To display the tags on screen, call these methods inside the onCreate() method:

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/SettingsFragment.java#L59-L60)
    ```java
    this.allTags = this.pusher.getTags() != null ? this.pusher.getTags() : new HashSet<String>();
    storeAllTags(this.allTags);
    ````

    The `storeAllTags(Set<String> tags)` method saves the tags in Preferences and populates the allTags attribute with all of the stored tags.

5. To display the tags on screen, call these methods inside the onCreate() method:

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/SettingsFragment.java#L115)
    ```java
    configureTags();
    ````

    The `configureTags()` method renders the tags section, a clickable EditTextPreference to add a new tag and the tags from allTags with checkboxes to enable/disable the tag.

<a name="0017"></a>
## Beacon and Geofence Messages Implementation

1. In your application’s app/build.gradle file add the following dependence (required for applications that will run on devices with Android OS < 5.0):
    
    [view the code](/app/build.gradle#L46-L47)
    ```gradle
    /* 3rd Party Libraries Required for SDK integration */
    compile 'com.radiusnetworks:AndroidIBeaconLibrary:0.7.6'
    ```
2. In your AndroidManifest, add the *JB4A SDK Permissions for location and region monitoring*, and the ETLocation Receiver and Service required to receive the push notifications based on the location of the customer.

    [view the code](/app/src/main/AndroidManifest.xml#L24)
    ```xml
    <!-- JB4A SDK Permissions for location and region monitoring -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    <!-- END JB4A SDK location and region monitoring Permissions -->
    …

    <!-- ETLocationReceiver and Service -->
    <receiver android:name="com.exacttarget.etpushsdk.ETLocationReceiver" >
        <intent-filter>
            <action android:name="android.location.PROVIDERS_CHANGED" />
            <category android:name="android.intent.category.DEFAULT" />
        </intent-filter>
    </receiver>

    <service
        android:name="com.exacttarget.etpushsdk.ETLocationService"
        android:enabled="true" />
    <!-- END ETLocationReceiver and Service -->
    ```
3. In your ApplicationClass, set the `LOCATION_ENABLED` parameter to true:

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/ApplicationClass.java#L56)
    ```java
    public static final boolean LOCATION_ENABLED = true;
    ```
4. In your ApplicationClass, set the `PROXIMITY_ENABLED` parameter to true:

    [view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/ApplicationClass.java#L52)
    ```java
    public static final boolean PROXIMITY_ENABLED = true;
    ```


<a name="0018"></a>
## Implement Analytics in your Mobile App

**ApplicationClass.java**

In your ApplicationClass, set the `ANALYTICS_ENABLED` parameter to true:

[view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/ApplicationClass.java#L56)
```java
public static final boolean ANALYTICS_ENABLED = true;
```

**BaseActivity.java**

Your app sends analytics whenever it goes into the background.  Override onPause() and onResume() in each Activity class to notify the SDK when activities pause and resume so the SDK can determine when your app goes into the background.

[view the code](/app/src/main/java/com/salesforce/marketingcloud/android/demoapp/BaseActivity.java#L15-L52)
```java
@Override
protected void onPause() {
   super.onPause();
   try {
       // Let JB4A SDK know when each activity paused
       ETPush.activityPaused(this);
   } catch (Exception e) {
       if (ETPush.getLogLevel() <= Log.ERROR) {
           Log.e(TAG, e.getMessage(), e);
       }
   }
}

@Override
protected void onResume() {
   super.onResume();
   try {
       // Let JB4A SDK know when each activity is resumed
       ETPush.activityResumed(this);
   } catch (Exception e) {
       if (ETPush.getLogLevel() <= Log.ERROR) {
           Log.e(TAG, e.getMessage(), e);
       }
   }
}
```

To see your new Web and Mobile Analytics, open the Web and Mobile Analytics app within the Marketing Cloud and agree to the Terms and Conditions to get started.

![image alt text](imgReadMe/image_30.png)
