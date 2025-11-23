//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

// Import necessary libraries for graphics, colors, and key events
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;

// Main class containing the game logic
public class Main {
    // Main method where the game starts
    public static void main(String[] args) {
        // Canvas properties, scale and set the canvas with the given parameters
        double xScale = 800.0, yScale = 400.0; // Define the dimensions of the canvas
        StdDraw.setCanvasSize(800, 400); // Set the canvas size
        StdDraw.setXscale(0.0, xScale); // Set the xScale-scale of the canvas
        StdDraw.setYscale(0.0, yScale); // Set the y-scale of the canvas

        // Color array for bricks (first import java.awt.Color )
        Color[] colors = { new Color(255, 0, 0), new Color(220, 20, 60),
                new Color(178, 34, 34), new Color(139, 0, 0),
                new Color(255, 69, 0), new Color(165, 42, 42)
        }; // Define an array of colors for the bricks

        // Game Components (These can be changed for custom scenarios)
        double ball_radius = 8; // Ball radius
        double ball_velocity = 5.3; // Magnitude of the ball velocity
        Color ball_color = new Color(15, 82, 186); // Color of the ball
        double[] initial_ball_pos = {400,18}; // Initial position of the ball in the format {xScale, y}
        double[] paddle_pos = {400, 5}; // Initial position of the center of the paddle
        double paddle_halfwidth = 40; // Paddle half width
        double paddle_halfheight = 5; // Paddle half height
        double paddle_speed = 30; // Paddle speed
        Color paddle_color = new Color(128, 128, 128); // Paddle color
        double brick_halfwidth = 50; // Brick half width
        double brick_halfheight = 10; // Brick half height

        // 2D array to store center coordinates of bricks in the format {xScale, y}
        // New brick positions with the top row lowered
        // New brick positions with the top row lowered
        // New brick positions with the top row lowered
        // 2D array to store center coordinates of bricks in the format {x, y}
        // 2D array to store center coordinates of bricks in the format {x, y}
        // 2D array to store center coordinates of bricks in the format {x, y}
        double[][] brick_coordinates = new double[][]{
                {100, 320}, {200, 320}, {300, 320}, {400, 320}, {500, 320}, {600, 320}, {700, 320}, // Top row
                {50, 300}, {150, 300}, {250, 300}, {350, 300}, {450, 300}, {550, 300}, {650, 300}, {750, 300}, // Second row
                {100, 280}, {200, 280}, {300, 280}, {400, 280}, {500, 280}, {600, 280}, {700, 280}, // Third row
                {50, 260}, {150, 260}, {250, 260}, {350, 260}, {450, 260}, {550, 260}, {650, 260}, {750, 260}, // Fourth row
                {100, 240}, {200, 240}, {300, 240}, {400, 240}, {500, 240}, {600, 240}, {700, 240}, // Fifth row
                {50, 220}, {150, 220}, {250, 220}, {350, 220}, {450, 220}, {550, 220}, {650, 220}, {750, 220}, // Sixth row
                {100, 200}, {200, 200}, {300, 200}, {400, 200}, {500, 200}, {600, 200}, {700, 200} // Seventh row
        };

// Array to store colors for each brick
        Color[] brick_colors = new Color[] {
                new Color(255, 0, 0),     // Red
                new Color(255, 165, 0),   // Orange
                new Color(255, 255, 0),   // Yellow
                new Color(0, 255, 0),     // Green
                new Color(0, 0, 255),     // Blue
                new Color(75, 0, 130),    // Indigo
                new Color(238, 130, 238), // Violet
                new Color(255, 0, 0),     // Red
                new Color(255, 165, 0),   // Orange
                new Color(255, 255, 0),   // Yellow
                new Color(0, 255, 0),     // Green
                new Color(0, 0, 255),     // Blue
                new Color(75, 0, 130),    // Indigo
                new Color(238, 130, 238), // Violet
                new Color(255, 0, 0),     // Red
                new Color(255, 165, 0),   // Orange
                new Color(255, 255, 0),   // Yellow
                new Color(0, 255, 0),     // Green
                new Color(0, 0, 255),     // Blue
                new Color(75, 0, 130),    // Indigo
                new Color(238, 130, 238), // Violet
                new Color(255, 0, 0),     // Red
                new Color(255, 165, 0),   // Orange
                new Color(255, 255, 0),   // Yellow
                new Color(0, 255, 0),     // Green
                new Color(0, 0, 255),     // Blue
                new Color(75, 0, 130),    // Indigo
                new Color(238, 130, 238), // Violet
                new Color(255, 0, 0),     // Red
                new Color(255, 165, 0),   // Orange
                new Color(255, 255, 0),   // Yellow
                new Color(0, 255, 0),     // Green
                new Color(0, 0, 255),     // Blue
                new Color(75, 0, 130),    // Indigo
                new Color(238, 130, 238), // Violet
                new Color(255, 0, 0),     // Red
                new Color(255, 165, 0),   // Orange
                new Color(255, 255, 0),   // Yellow
                new Color(0, 255, 0),     // Green
                new Color(0, 0, 255),     // Blue
                new Color(75, 0, 130),    // Indigo
                new Color(238, 130, 238), // Violet
                new Color(255, 0, 0),     // Red (added to match length)
                new Color(255, 165, 0),    // Orange (added to match length)
                new Color(255, 255, 0),   // Yellow
                new Color(0, 255, 0),     // Green
                new Color(0, 0, 255),     // Blue
                new Color(75, 0, 130),    // Indigo
                new Color(238, 130, 238), // Violet
                new Color(255, 0, 0),     // Red (added to match length)
                new Color(255, 165, 0),  // Orange (added to match length)
                new Color(255, 255, 0),   // Yellow
        };

        // Enable double buffering for smooth rendering
        StdDraw.enableDoubleBuffering();
        int pauseDuration = 12; // Duration between frames in milliseconds
        boolean[] destroyedBricks = new boolean[brick_coordinates.length]; // Track which bricks are destroyed
        boolean isThrown = false; // Track if the ball has been thrown
        boolean isItOver = false; // Track if the game is over
        double throwAngle = 90; // Initial throw angle of the ball
        int LineLength = 40; // Length of the line indicating the throw angle

        // Loop to handle the pre-throw phase
        while (!isThrown){
            StdDraw.clear(StdDraw.WHITE); // Clear the canvas with white color

            // Draw all bricks
            for(int i=0; i<brick_colors.length; i++) {
                StdDraw.setPenColor(brick_colors[i]);
                StdDraw.filledRectangle(brick_coordinates[i][0], brick_coordinates[i][1], brick_halfwidth, brick_halfheight);
            }

            // Draw the ball
            StdDraw.setPenColor(ball_color);
            StdDraw.filledCircle(initial_ball_pos[0],initial_ball_pos[1],ball_radius);

            // Draw the paddle
            StdDraw.setPenColor(paddle_color);
            StdDraw.filledRectangle(paddle_pos[0],paddle_pos[1],paddle_halfwidth,paddle_halfheight);

            // Handle key presses to adjust the throw angle
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                if (throwAngle < 180.0) {
                    throwAngle = throwAngle + 1.0;
                }
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                if (throwAngle > 0.0) {
                    throwAngle = throwAngle - 1.0;
                }
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                isThrown = true; // Throw the ball when space is pressed
            }

            // Display the current throw angle
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.textLeft(10, yScale -20,"Angle:"+ throwAngle);

            // Draw the line indicating the throw angle
            double throwAngleRadians = Math.toRadians(throwAngle);
            StdDraw.setPenColor(StdDraw.RED); // set pen color
            StdDraw.setPenRadius(0.005); // set pen size
            StdDraw.line(initial_ball_pos[0],initial_ball_pos[1],initial_ball_pos[0]+LineLength*Math.cos(throwAngleRadians),initial_ball_pos[1]+LineLength*Math.sin(throwAngleRadians));

            // Show the current frame and pause for a short duration
            StdDraw.show();
            StdDraw.pause(pauseDuration);
        }

        // Variables for post-throw phase

        double throwAngleRadians = Math.toRadians(throwAngle);
        double ball_velocity_x = ball_velocity * Math.cos(throwAngleRadians); // Ball velocity in xScale direction
        double ball_velocity_y = ball_velocity * Math.sin(throwAngleRadians); // Ball velocity in y direction
        int score = 0; // Player's score
        boolean victory = false; // Track if the player has won
        boolean isPaused = false; // Track whether the game is paused
        boolean spacePressed = false; // Track if the space key is pressed
        long frame = 0; // Frame counter

        // Main game loop
        while (!isItOver){
            frame += 1; // Increment frame counter

            // Handle pause functionality
            if (frame >10) {
                if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                    if (!spacePressed) {
                        isPaused = !isPaused; // Toggle pause state
                        spacePressed = true;
                    }
                } else {
                    spacePressed = false;
                }

                // If paused, display "PAUSED" and skip the rest of the loop
                if (isPaused) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(37, yScale - 12, "PAUSED");
                    StdDraw.show();
                    StdDraw.pause(pauseDuration);
                    continue;
                }
            }

            // Clear the canvas
            StdDraw.clear(StdDraw.WHITE);

            // Handle paddle movement
            if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
                if (paddle_pos[0] + paddle_speed + paddle_halfwidth<= xScale) {
                    paddle_pos[0] = paddle_pos[0] + paddle_speed;
                }
            }
            if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
                if (paddle_pos[0] - paddle_speed - paddle_halfwidth >= 0) {
                    paddle_pos[0] = paddle_pos[0] - paddle_speed;
                }
            }


            // Handle ball collision with walls
            if ((initial_ball_pos[1] + ball_velocity_y) >= yScale - ball_radius) {
                ball_velocity_y = -ball_velocity_y; // Reverse y velocity if ball hits the top wall
            }
            if ((initial_ball_pos[1]) <= 0.0 ) {
                isItOver = true; // End game if ball hits the bottom wall
                victory = false;
            }
            if ((initial_ball_pos[0] + ball_velocity_x) >= xScale - ball_radius) {
                ball_velocity_x = -ball_velocity_x; // Reverse xScale velocity if ball hits the right wall
            }
            if ((initial_ball_pos[0] + ball_velocity_x) <= 0.0 + ball_radius) {
                ball_velocity_x = -ball_velocity_x; // Reverse xScale velocity if ball hits the left wall
            }

            // Define collision points around the ball
            double[][] hitboxreference = new double[][]{{initial_ball_pos[0] + ball_velocity_x-ball_radius,initial_ball_pos[1] + ball_velocity_y}, //point 0
                    {initial_ball_pos[0] + ball_velocity_x,initial_ball_pos[1] + ball_velocity_y-ball_radius}, //point 1
                    {initial_ball_pos[0] + ball_velocity_x + ball_radius,initial_ball_pos[1] + ball_velocity_y}, //point 2
                    {initial_ball_pos[0] + ball_velocity_x,initial_ball_pos[1] + ball_velocity_y + ball_radius}}; //point 3

            // Variables for corner collision detection
            double corner_x = 0;
            double corner_y = 0;
            boolean isCornerCollision = false;

            // Check for collisions with bricks
            for (int i = 0; i < brick_coordinates.length; i++) {
                if (!destroyedBricks[i]) {
                    double brick_x = brick_coordinates[i][0];
                    double brick_y = brick_coordinates[i][1];

                    // Define the corners of the brick
                    double[][] brick_corners = {
                            {brick_x - brick_halfwidth, brick_y - brick_halfheight},  // Bottom-left
                            {brick_x + brick_halfwidth, brick_y - brick_halfheight},  // Bottom-right
                            {brick_x - brick_halfwidth, brick_y + brick_halfheight},  // Top-left
                            {brick_x + brick_halfwidth, brick_y + brick_halfheight}   // Top-right
                    };

                    // Check if the ball hit any of the corners
                    boolean SameLocationCorner = false;
                    for (int j = 0; j < brick_corners.length; j++) {
                        corner_x = brick_corners[j][0];
                        corner_y = brick_corners[j][1];
                        double dist = Math.sqrt(Math.pow(initial_ball_pos[0] + ball_velocity_x - corner_x, 2) + Math.pow(initial_ball_pos[1] + ball_velocity_y - corner_y, 2));

                        if (dist <= ball_radius) {

                            for(int k = 0; k < brick_coordinates.length; k++){
                                if (!destroyedBricks[k] && k != i) {
                                    double checkbrick_x = brick_coordinates[k][0];
                                    double checkbrick_y = brick_coordinates[k][1];

                                    // Define the corners of the other brick for checking does it share the same corner coordinates
                                    double[][] checkbrick_corners = {
                                            {checkbrick_x - brick_halfwidth, checkbrick_y - brick_halfheight},  // Bottom-left
                                            {checkbrick_x + brick_halfwidth, checkbrick_y - brick_halfheight},  // Bottom-right
                                            {checkbrick_x - brick_halfwidth, checkbrick_y + brick_halfheight},  // Top-left
                                            {checkbrick_x + brick_halfwidth, checkbrick_y + brick_halfheight}   // Top-right
                                    };
                                    for(int n = 0; n < checkbrick_corners.length;n++ ){
                                        double checkCorner_x = checkbrick_corners[n][0];
                                        double checkCorner_y = checkbrick_corners[n][1];
                                        if (corner_x == checkCorner_x && corner_y == checkCorner_y){

                                            SameLocationCorner = true;
                                            break;
                                        }
                                    }
                                }
                            }

                            if (!SameLocationCorner) {

                                isCornerCollision = true;
                                destroyedBricks[i] = true;
                                score += 10;
                                break;
                            } else {
                                isCornerCollision = false;
                            }
                        }
                    }
                }
            }

            // Handle corner collision
            if (isCornerCollision) {
                // Normalize normal vector

                double normal_x, normal_y;
                normal_x = initial_ball_pos[0] - ball_velocity_x - corner_x;
                normal_y = initial_ball_pos[1] - ball_velocity_y - corner_y;

                double norm_length = Math.sqrt((normal_x * normal_x) + (normal_y * normal_y));
                normal_x /= norm_length;
                normal_y /= norm_length;

                // Reflect velocity using formula: V' = V - 2(V . N)N
                double dotProduct = ball_velocity_x * normal_x + ball_velocity_y * normal_y;
                ball_velocity_x -= 2 * dotProduct * normal_x;
                ball_velocity_y -= 2 * dotProduct * normal_y;
            } else{
                // Handle regular collisions with bricks
                boolean directionChangeVertical = false;
                boolean directionChangeHorizontal = false;
                for (int c = 0; c < hitboxreference.length; c++) {
                    for (int i = 0; i < brick_coordinates.length; i++) {
                        if (!destroyedBricks[i]) {
                            if ((brick_coordinates[i][0] - brick_halfwidth <= hitboxreference[c][0]) && (hitboxreference[c][0] <= brick_coordinates[i][0] + brick_halfwidth)) {
                                if ((brick_coordinates[i][1] - brick_halfheight <= hitboxreference[c][1]) && (hitboxreference[c][1] <= brick_coordinates[i][1] + brick_halfheight)) {
                                    destroyedBricks[i] = true;
                                    score += 10;

                                    if ((c == 0) || (c == 2) && (!directionChangeHorizontal)) {
                                        ball_velocity_x = -ball_velocity_x;
                                        directionChangeHorizontal = true;
                                    }

                                    if ((c == 1) || (c == 3) && (!directionChangeVertical)) {
                                        ball_velocity_y = -ball_velocity_y;
                                        directionChangeVertical = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Define the corners of the paddle
            double[][] paddleCorners = {
                    {paddle_pos[0] - paddle_halfwidth, paddle_pos[1] - paddle_halfheight}, // Bottom-left
                    {paddle_pos[0] + paddle_halfwidth, paddle_pos[1] - paddle_halfheight}, // Bottom-right
                    {paddle_pos[0] - paddle_halfwidth, paddle_pos[1] + paddle_halfheight}, // Top-left
                    {paddle_pos[0] + paddle_halfwidth, paddle_pos[1] + paddle_halfheight}  // Top-right
            };

            // Check for corner collisions with the paddle
            boolean isPaddleCornerCollision = false;
            for (int i = 0; i < paddleCorners.length; i++) {
                corner_x = paddleCorners[i][0];
                corner_y = paddleCorners[i][1];
                double dist = Math.sqrt(Math.pow(initial_ball_pos[0] + ball_velocity_x - corner_x, 2) + Math.pow(initial_ball_pos[1] + ball_velocity_y - corner_y, 2));

                if (dist <= ball_radius) {
                    isPaddleCornerCollision = true;
                    break;
                }
            }

            // Handle paddle corner collision
            if (isPaddleCornerCollision) {
                // Reflect the ball's velocity based on the normal vector at the collision point
                double normal_x = initial_ball_pos[0] - 2 * ball_velocity_x - paddle_pos[0];
                double normal_y = initial_ball_pos[1] - 2 * ball_velocity_y - paddle_pos[1];
                double norm_length = Math.sqrt((normal_x * normal_x) + (normal_y * normal_y));
                normal_x /= norm_length;
                normal_y /= norm_length;

                double dotProduct = ball_velocity_x * normal_x + ball_velocity_y * normal_y;
                ball_velocity_x -= 2 * dotProduct * normal_x;
                ball_velocity_y -= 2 * dotProduct * normal_y;
            } else {
                // Regular paddle collision detection
                if ((paddle_pos[0] - paddle_halfwidth < initial_ball_pos[0] + ball_velocity_x - ball_radius + 1) && (initial_ball_pos[0] + ball_velocity_x - ball_radius < paddle_pos[0] + paddle_halfwidth + 1)) {
                    if ((paddle_pos[1] - paddle_halfheight < initial_ball_pos[1] + ball_velocity_y - ball_radius + 1) && (initial_ball_pos[1] + ball_velocity_y - ball_radius < paddle_pos[1] + paddle_halfheight + 1)) {
                        ball_velocity_y = -ball_velocity_y;
                    }
                }
            }

            // Update the ball's position
            initial_ball_pos[0] = initial_ball_pos[0] + ball_velocity_x;
            initial_ball_pos[1] = initial_ball_pos[1] + ball_velocity_y;

            // Draw the ball
            StdDraw.setPenColor(ball_color);
            StdDraw.filledCircle(initial_ball_pos[0],initial_ball_pos[1],ball_radius);

            // Draw the paddle
            StdDraw.setPenColor(paddle_color);
            StdDraw.filledRectangle(paddle_pos[0],paddle_pos[1],paddle_halfwidth,paddle_halfheight);

            // Draw the remaining bricks
            for(int i=0; i<brick_colors.length; i++) {
                if (!destroyedBricks[i]) {
                    StdDraw.setPenColor(brick_colors[i]);
                    StdDraw.filledRectangle(brick_coordinates[i][0], brick_coordinates[i][1], brick_halfwidth, brick_halfheight);
                }
            }

            // Display the score
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(760, 390, "Score: "+ score);

            // Check if all bricks are destroyed
            boolean allBricksDestroyed = true;
            for (int i = 0; i < destroyedBricks.length; i++) {
                if (!destroyedBricks[i]) {
                    allBricksDestroyed = false;
                    break;
                }
            }
            if (allBricksDestroyed) {
                isItOver = true;
                victory = true;
            }

            // Show the current frame and pause for a short duration
            StdDraw.show();
            StdDraw.pause(pauseDuration);
        }

        // Clear the score display area
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(xScale -50, yScale -20,50,20);

        // Define fonts for the end game message
        Font scoreFont = new Font("Serif", Font.BOLD, 30);
        Font font = new Font("Serif", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.BLACK);

        // Display victory or game over message
        if (victory){
            StdDraw.text(xScale /2, yScale /2,"VICTORY!");
            StdDraw.setFont(scoreFont);
            StdDraw.text(xScale /2, yScale /6, "Score: " + score);
        } else{
            StdDraw.text(xScale /2, yScale /3.5, "-GAME OVER-");
            StdDraw.setFont(scoreFont);
            StdDraw.text(xScale /2, yScale /6, "Score: " + score);
        }

        // Show the final frame
        StdDraw.show();
    }
}