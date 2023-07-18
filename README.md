<h2>FundooNotes App</h2>
<br>
<h4>
  This application is a backend-only notes app, which lets you do following actions:
  <br><br>
  <ul>
    <li>Create a new note</li>
    <li>Delete a note</li>
    <li>Update a note</li>
    <li>ArchiveNote a note</li>
    <li>Pin/Unpin a note</li>
    <li>Restore a deleted note</li>
    <li>Add collaborators to your note</li>
    <li>Add labels to your note</li>
  </ul>
  <br><br>

  Learning from this project:
  <ul>
    <li>Used JWT token for authentication</li>
    <li>User will be able to sort notes according to the labels, implementing this helped to get an idea about various CRUD operations </li>
    <li>User can add multiple lables and collaborators to a note, implementing this helped to get an idea about Many-to-Many mapping in Models</li>
    <li>All the responses are returned in JSON format, so that it is easier to work with front-end as well.</li>
  </ul>
  <br>
  <h4>Technologies used:</h4>
  <ol>
    <li>Java</li>
    <li>SpringBoot</li>
  </ol
  <br><br>
  <h4>To run the application locally, pleae follow these steps:</h4>
  <ol>
    <li>Please try to use Spring STS IDE for less configurations</li>
    <li>Clone the repo locally and open in above IDE</li>
    <li>Make changes according to your local Database and SMTP properties inside application.properties file at given path : <u>src/main/resources
/application.properties
</u></li>
    <li>Run the application.</li>
  </ol>
</h4>
