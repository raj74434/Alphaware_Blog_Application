# Alphaware_Blog_Application
 <h2>Overview</h2>
    <p>This project is a blog application developed using Spring Boot, Maven, and Swagger. It allows users to create, view, edit, and delete blog posts along with commenting on posts. The application supports two types of users: admin and regular users. Admin users have access to all API endpoints while regular users have limited access.</p>
<div>
    <h2>Technologies Used</h2>
    <ul>
        <li>Spring Boot</li>
        <li>Maven</li>
        <li>Swagger</li>
        <li>MySQL</li>
    </ul>

    <h2>Entities</h2>
    <ol>
        <li><strong>User</strong>: Represents users of the application.</li>
        <li><strong>Post</strong>: Represents blog posts created by users.</li>
        <li><strong>Comment</strong>: Represents comments made on blog posts.</li>
        <li><strong>Category</strong>: Represents categories for organizing blog posts.</li>
    </ol>

    <h2>Users</h2>
    <p>There are two types of users:</p>
    <ul>
        <li><strong>Admin</strong>: Has full access to all API endpoints.</li>
        <li><strong>User</strong>: Regular users with limited access to API endpoints.</li>
    </ul>

    <h2>Database</h2>
    <p>The application uses MySQL as the database to store user information, blog posts, comments, and categories.</p>

    <h2>Swagger</h2>
    <p>Swagger is integrated into the application to provide a user-friendly interface for viewing and interacting with the API endpoints. It allows users to explore the available endpoints, their parameters, and responses.</p>

    <h2>API Endpoints</h2>
    <p>The following API endpoints are available in the application:</p>
    <ol>
        <li><code>/api/user </code>
            <ul>
                <li>GET: Get all users</li>
                <li> Sub-end point :- /register  & Method POST : Create a new user</li>
                <li> Sub-end point :- /login  & Method POST : login a user by jwt tocken </li>
                <li>PUT: Update an existing user</li>
                <li>DELETE: Delete a user</li>
                
            </ul>
        </li>
        <li><code>/api/posts</code>
            <ul>
                <li>GET: Get all blog posts</li>
                <li>POST: Create a new blog post</li>
                <li>PUT: Update an existing blog post</li>
                <li>DELETE: Delete a blog post</li>
            </ul>
        </li>
        <li><code>/api/comments</code>
            <ul>
                <li>GET: Get all comments</li>
                <li>POST: Add a new comment to a blog post</li>
                <li>PUT: Update an existing comment</li>
                <li>DELETE: Delete a comment</li>
            </ul>
        </li>
        <li><code>/api/categories</code>
            <ul>
                <li>GET: Get all categories</li>
                <li>POST: Create a new category</li>
                <li>PUT: Update an existing category</li>
                <li>DELETE: Delete a category</li>
            </ul>
        </li>
    </ol>

    <h2>How to Run</h2>
    <p>To run the application, follow these steps:</p>
    <ol>
        <li>Clone the repository.</li>
        <li>Set up the MySQL database and configure the database connection in the application properties.</li>
        <li>Build the project using Maven.</li>
        <li>Run the application using the generated JAR file.</li>
        <li>Access the Swagger UI to interact with the API endpoints.</li>
    </ol>

</div>
    
