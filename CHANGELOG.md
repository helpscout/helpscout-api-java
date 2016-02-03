### 1.6.0 (February 2, 2016)

* Added Custom Fields and Teams support (Pro Plan features)
   * there are new methods `getTeam` and `getTeams`
   * Custom Field responses were added to `Conversation` class
   * Custom Fields were added to `Mailbox` class
   * subclasses of `CustomFieldResponse` provide typed access to different custom field types. See [API documentation](http://developer.helpscout.net/help-desk-api/objects/field/)
* Added more tests for the ApiClient

#### Class model change

API methods that used to return `User` now return `MailboxUser`. `User` and new class `Team` are subtypes of `MailboxUser`.

Affected methods are:
* `getUser()`
* `getUsers()`
* `getUsersForMailbox()`
* `LineItem.getAssignedTo()`, `LineItem.setAssignedTo()` - `LineItem` is an interface with several subclasses

Affected field:
* `Conversation.owner`

There is also new dependency - Joda Time
``` xml
		<dependency>
			<groupId>joda-time</groupId>
			<artifactId>joda-time</artifactId>
			<version>2.9.1</version>
			<scope>compile</scope>
		</dependency>
```

### 1.5.1 (October 28, 2015)

* Address model has been updated to use java.util.Data instead of java.util.Calendar objects, de-serialization from JSON payloads will now work correctly.


### 1.5.0 (August 27, 2015)

* Identifiers are now of type `Long` (matching model objects). Old methods using `Integer` are deprecated.
* Added support for generics.
* Lombok-ified many classes.
* Added `toString()` methods to model objects.
* The build now also generates a source JAR. 
* Reformatted code to use spaces instead of tabs.
* Used `Base64` encoder from Apache Commons Codec insead of "sun.misc" package. Fixes [issue #2](https://github.com/helpscout/helpscout-api-java/issues/2).
* Initialized `ApiClient` instance at class-load time and refactored `ApiClient.getInstance()`. Fixes [issue #6](https://github.com/helpscout/helpscout-api-java/issues/6).
* Fixed error in example `CreateConversation.java`, and also improved exception messages in the case of bad requests. Fixes [issue #8](https://github.com/helpscout/helpscout-api-java/issues/8).

Thanks to our many contributors who submitted pull requests for this release!

### 1.4.0 (June 22, 2015)

* Added support for getting report data. See [developer docs](http://developer.helpscout.net/help-desk-api/reports/conversations/conversations/) for more information.

#### 1.3.13 (April 25, 2014)

* Added support for updating the body text of a thread. See [developer docs](http://developer.helpscout.net/) for more information.

#### 1.3.11 (February 4, 2014)

* Added method to get the current User associated with the API used to make the request.

#### 1.3.10 (October 21, 2013)

* Added endpoint to delete a note.

#### 1.3.9 (August 27 14, 2013)

* Added support for the thread-source endpoint (to retrieve original email source). See [developer docs](http://developer.helpscout.net/conversations/thread/source/) for more information.

#### 1.3.8 (May 14, 2013)

* Added support for workflows (get workflows, run a manual workflow). See [developer docs](http://developer.helpscout.net/workflows/list/) for more information.

#### 1.3.7 (May 2, 2013)

* Added support for 'imported' and 'workflow' action type on line items.

#### 1.3.6 (March 1, 2013)

* Updated with support for new line item properties (actionType and actionSourceId). See [developer docs](http://developer.helpscout.net/) for more information.

#### 1.3.5 (January 21, 2013)

* Conversations and threads can now be marked as 'imported' at creation time.

#### 1.3.4 (December 7, 2012)

* Added 'phone' conversation and thread type.

#### 1.3.3 (November 5, 2012)

* Added a method to retrieve a list of customers for a mailbox.

#### 1.3.2 (October 31, 2012)

* Added examples for write endpoints (create/update conversation, create/update customer).

#### 1.3.1 (October 26, 2012)

* Bug fix: Added setters to Address model object.

#### 1.3.0 (October 25, 2012)

* Conversation write endpoints added. Conversations can now be created, updated, and deleted. Threads can be created, and attachments can be created and associated with a thread.
* Customer write endpoints added. Customers can now be created and updated.
* Customers can now be searched for by name and/or email.

#### 1.2.0 (October 16, 2012)

* Conversation now has a [ConversationType](https://github.com/helpscout/helpscout-api-java/blob/master/src/main/java/net/helpscout/api/cbo/ConversationType.java) property that specifies if the type of conversation is an 'email' or 'chat'.
* PersonRef now has a [PersonType](https://github.com/helpscout/helpscout-api-java/blob/master/src/main/java/net/helpscout/api/cbo/PersonType.java) property that specifies if a 'user' or 'customer' is represented.
* ThreadType has been updated to include a Chat conversation.
