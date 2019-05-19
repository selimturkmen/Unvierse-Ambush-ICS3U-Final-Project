package application;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
 * ICS3U Final Project
 * 'Universe Ambush'
 * 
 * By Selim Turkmen and Simon Malone
 * 
 * Submitted to Mr. Patel
 * 
 */
public class Game extends Application {

	// Objects used, or might be used through different methods
	static AnchorPane anchorPane;
	static ArrayList<Rectangle> bullets;
	static ArrayList<Rectangle> ebullets;
	static Rectangle enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, shield, player;
	static int points;
	static int j;
	static int level = 0;
	static double speed = 1;
	static double espeed = 11;
	static Label score;
	static int win;
	static int x = 0;
	static int transition = 0;
	public static Stage stage = new Stage();

	/*
	 * Primary Stage, immediatly moves to menu method, which is the start screen
	 * Process: Program Starts --> Primary stage runs, immediately opens menu method
	 */
	public void start(Stage stage) throws Exception {

		menu();
	}

	/*
	 * menu Method, the start screen and first screen the player sees.
	 */
	public static void menu() {

		// Sets size of the screen, scene setup, and colour
		Group root = new Group();
		Scene scene = new Scene(root, 750, 750, Color.SALMON);
		// The title (the words on the top of the window)
		stage.setTitle("UNIVERSE AMBUSH - Selim Turkmen and Simon Malone");

		// Start Button - Takes you to the other scene
		Button Start = new Button("Start");

		// Size and coordinates of the button
		Start.setTranslateX(300);
		Start.setTranslateY(200);
		Start.prefHeight(30);
		Start.prefWidth(40);
		Start.setStyle("-fx-background-color: White");

		Start.setOnAction(e -> {
			stage.close();
			game();
		});

		// Controls Button - Takes you to controls and information scene
		Button Controls = new Button("Controls");

		// Size and coordinates of the button
		Controls.setTranslateX(300);
		Controls.setTranslateY(300);
		Controls.prefHeight(30);
		Controls.prefWidth(40);
		Controls.setStyle("-fx-background-color: White");

		// Closes current stage and opens "controls" method which is the controls and
		// instructions of the game
		Controls.setOnAction(e -> {
			stage.close();
			controls();
		});

		// Exit Button - Closes Game and program
		Button Exit = new Button("Exit");

		// Size and coordinates of the button
		Exit.setTranslateX(300);
		Exit.setTranslateY(400);
		Exit.prefHeight(30);
		Exit.prefWidth(40);
		Exit.setStyle("-fx-background-color: White");

		// Closes current stage and opens "controls" method
		Exit.setOnAction(e -> {
			stage.close();
		});

		// label with the title of the game, w/ settings - Prints "UNIVERSE AMBUSH"
		Label title = new Label("UNIVERSE AMBUSH");
		title.setTranslateX(150);
		title.setMinWidth(50);
		title.setMinHeight(50);
		title.setFont(new Font("Arial", 50));
		title.setTextFill(Color.web("#FFFFFF"));

		// Animation for the title, to make it fade in and out
		FadeTransition fadeTitle = new FadeTransition(Duration.seconds(1.25), title);
		// From value 1.0 -> 0.0 , from 100% opacity to 0%
		fadeTitle.setFromValue(1.0);
		fadeTitle.setToValue(0.0);
		// Keeps it going forever
		fadeTitle.setCycleCount(Animation.INDEFINITE);
		// Reverse back, so it fades out and fades in
		fadeTitle.setAutoReverse(true);
		fadeTitle.play();

		root.getChildren().addAll(Start);
		root.getChildren().addAll(Controls);
		root.getChildren().add(Exit);
		root.getChildren().addAll(title);

		stage.setScene(scene);
		stage.show();

	}

	/*
	 * The first level and base game method
	 */
	public static void game() {

		// Makes level == 1
		level = 1;
		// Array lists for the bullets, bullets == players bullets, ebullets == enemies
		// bullets
		bullets = new ArrayList();
		ebullets = new ArrayList();

		/*
		 * making x zero so the player moves the same amount and doesnt jump around
		 * points are zero, so it displays zero on the score label speed of the bullet
		 * to 1 (lower is faster) espeed, speed of the enemy bullet set to 10 (higher is
		 * slower) win set to zero, so it means the game is in progress
		 */

		x = 0;
		points = 0;
		speed = 1;
		espeed = 10;
		win = 0;

		// Player dimensions, 50x50 and setting the colour red
		player = new Rectangle(50, 50, Color.BLUE);

		// Setting up the first enemy, 50x50 and colour red
		// Enemy that moves left to right
		enemy1 = new Rectangle(50, 50, Color.RED);
		enemy1.setLayoutX(250);
		enemy1.setLayoutY(200);

		// the animation for the enemy
		// Duration == 2seconds
		TranslateTransition em = new TranslateTransition(Duration.millis(2000), enemy1);
		// moves 300 to the right (adds 300 to x)
		em.setByX(300);
		// Keeps it going
		em.setCycleCount(Animation.INDEFINITE);
		// Reverses back to original spot
		em.setAutoReverse(true);
		em.play();

		// Setting up the first enemy, 50x50 and colour red
		// Enemy on the RIGHT
		enemy2 = new Rectangle(50, 50, Color.RED);
		enemy2.setLayoutX(500);
		enemy2.setLayoutY(300);

		// Setting up the first enemy, 50x50 and colour red
		// Enemy on the LEFT
		enemy3 = new Rectangle(50, 50, Color.RED);
		enemy3.setLayoutX(200);
		enemy3.setLayoutY(300);

		// declares up the pane
		anchorPane = new AnchorPane();
		Scene scene = new Scene(anchorPane, 750, 750);

		// Player at the middle and very bottom of screen
		player.setLayoutX(350);
		player.setLayoutY(700);
		stage.setScene(scene);
		stage.show();

		// Score Label, Prints "Score: " on the screen with the value of points int next
		// to it
		// Label is at the top left of the screen, moved away slightly, size 20 font,
		// and coloured black
		score = new Label("Score: " + points);
		score.setMinWidth(50);
		score.setMinHeight(50);
		score.setFont(new Font("Arial", 20));
		score.setTextFill(Color.web("#000000"));

		// Sets up the shield, right above the character, 10 tall, 65 wide, colored
		// chocolate
		shield = new Rectangle(65, 10, Color.CHOCOLATE);
		shield.setLayoutX(330);
		shield.setLayoutY(625);

		// The movement, shooting, and enemy shooting \/

		// Seeing if any key is pressed
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

			/*
			 * Start of player controls
			 */

			// if you press the Right arrow key run the following code
			if (event.getCode() == KeyCode.RIGHT) {

				// Only run the code if x is less than 350, because if it equals 350, it gets to
				// the edge of the screen
				// This code adds 10 to x, then adds x to the player's x coordinate which is 0.0
				if (x < 350) {
					x += 10;
					player.setTranslateX(player.getX() + x);
				}
			}

			// if you press the left arrow key run the following code
			if (event.getCode() == KeyCode.LEFT) {

				// Only run the code if x is greater than -350, because if it equals -350, it
				// gets to the edge of the screen
				// This code adds -10 to x, then adds x to the player's x coordinate which is
				// 0.0
				if (x > -350) {
					x -= 10;
					player.setTranslateX(player.getX() + x);
				}
			}

			// SHOOTIGN
			// Code runs when you press Space
			if (event.getCode() == KeyCode.SPACE) {
				// bullet is 5x10 and coloured orange
				bullets.add(new Rectangle(5, 10, Color.ORANGE));
				int i = bullets.size() - 1;
				// Bullet layout is at 700 (top of the player) and the MinX of player + 25
				// (middle of player)
				bullets.get(i).setLayoutX(player.getBoundsInParent().getMinX() + 25);
				bullets.get(i).setLayoutY(700);
				anchorPane.getChildren().add(bullets.get(i));
				// goes to animate bullet method
				animateBullet(bullets.get(i));
			}

			/*
			 * End of player controls
			 */

			// eb is the same as 'i' above
			int eb = ebullets.size() - 1;
			if (level >= 1) {

				// Same code as above, just reversed so it starts at the enemy, and shoots down
				// at the player

				// If the player presses the right key, all the odd numbered enemies shoot at
				// the player
				if (event.getCode() == KeyCode.RIGHT) {

					ebullets.add(new Rectangle(5, 10, Color.SALMON));
					eb = ebullets.size() - 1;
					ebullets.get(eb).setLayoutX(enemy1.getBoundsInParent().getMinX() + 25);
					ebullets.get(eb).setLayoutY(enemy1.getBoundsInParent().getMaxY());
					anchorPane.getChildren().add(ebullets.get(eb));
					eanimateBullet(ebullets.get(eb));

					ebullets.add(new Rectangle(5, 10, Color.SALMON));
					eb = ebullets.size() - 1;
					ebullets.get(eb).setLayoutX(enemy3.getBoundsInParent().getMinX() + 25);
					ebullets.get(eb).setLayoutY(enemy3.getBoundsInParent().getMaxY());
					anchorPane.getChildren().add(ebullets.get(eb));
					eanimateBullet(ebullets.get(eb));
				}

				// If the player presses the left key, all the even numbered enemies shoot at
				// the player
				if (event.getCode() == KeyCode.LEFT) {

					ebullets.add(new Rectangle(5, 10, Color.SALMON));
					eb = ebullets.size() - 1;
					ebullets.get(eb).setLayoutX(enemy2.getBoundsInParent().getMinX() + 25);
					ebullets.get(eb).setLayoutY(enemy2.getBoundsInParent().getMaxY());
					anchorPane.getChildren().add(ebullets.get(eb));
					eanimateBullet(ebullets.get(eb));
				}

			}

		});

		anchorPane.getChildren().add(player);
		anchorPane.getChildren().add(shield);
		anchorPane.getChildren().add(score);
		anchorPane.getChildren().add(enemy1);
		anchorPane.getChildren().add(enemy2);
		anchorPane.getChildren().add(enemy3);
	}

	/*
	 * Controls screen, accessed by the controls button on the menu method
	 */
	public static void controls() {

		Group root = new Group();
		Scene scene2 = new Scene(root, 750, 750, Color.SALMON);

		// Button to bring it back to the menu, on top left
		Button Back = new Button("Main Menu");
		Back.setTranslateX(0);
		Back.setTranslateY(0);
		Back.prefHeight(30);
		Back.prefWidth(40);
		Back.setStyle("-fx-background-color: White");

		Back.setOnAction(e -> {
			stage.close();
			menu();
		});

		/*
		 * Image of controls menu
		 */
		// Creating an image
		Image bg = new Image("Background.png");

		// Setting the image view
		ImageView bg1 = new ImageView(bg);

		// Setting the position of the image
		bg1.setX(0);
		bg1.setY(0);

		// setting the fit height and width of the image view
		bg1.setFitHeight(750);
		bg1.setFitWidth(750);

		root.getChildren().add(bg1);
		root.getChildren().addAll(Back);

		stage.setScene(scene2);
		stage.show();

	}

	/*
	 * 
	 * the enemy bullets, and its animation, always running, Code for Timeline and
	 * KeyFrame
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/animation/KeyFrame.html
	 * 
	 */
	static void eanimateBullet(final Rectangle ebullet) {

		// Create a new Timeline - literally a timeline of the bullet
		Timeline timeline = new Timeline();
		// How long (in milliseconds) the bullet will shoot for
		timeline.setCycleCount(800);
		// Essentially, decides speed of the bullet
		// duration is the duration of the bullets animation
		final KeyFrame kf = new KeyFrame(Duration.millis(espeed), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// adds 1 to y value, meaning it goes down to the player
				ebullet.setY(ebullet.getY() + 1);
				// if the bullet is ever in the bounds on the 'player' rectangle...
				if (ebullet.getBoundsInParent().intersects(player.getBoundsInParent())) {
					// make player invisible and move it off the screen
					player.setVisible(false);
					player.setLayoutX(2000);
					player.setLayoutY(2000);

					// Make the bullet invisible and move it away
					ebullet.setVisible(false);
					ebullet.setLayoutX(2000);
					// Make win = 2, which means the player lost
					win = 2;

				}
				// once win is equal to 2, switch the scene to the lose method
				// if the transition = 1, meaning the player is not in a transition screen, go
				// to the lose method
				if (win == 2 && transition == 0) {
					lose();
				}
				// if the bullet intersects the bounds of the shield, make the bullet invisible
				// and move it away
				if (ebullet.getBoundsInParent().intersects(shield.getBoundsInParent())) {
					ebullet.setVisible(false);
					ebullet.setLayoutX(2000);

				}

			}
		});

		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	/*
	 * 
	 * the bullets, and its animation, always running Code for Timeline and KeyFrame
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/animation/Timeline.html
	 * https://docs.oracle.com/javase/8/javafx/api/javafx/animation/KeyFrame.html
	 * 
	 */
	static void animateBullet(final Rectangle bullet) {

		// Create a new Timeline - literally a timeline of the bullet
		Timeline timeline = new Timeline();
		// How long (in milliseconds) the bullet will shoot for
		timeline.setCycleCount(710);
		// Essentially, decides speed of the bullet, the amount of miliseconds it moves
		// for
		final KeyFrame kf = new KeyFrame(Duration.millis(speed), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// subtracts 1 from y, meaning it goes up
				bullet.setY(bullet.getY() - 1);
				/*
				 * For all the code below: * if the bullet intersects with the bounds of the
				 * enemies, the enemies turn invisible and move off the screen, and so does the
				 * bullet * Once you hit an enemy, 100 is added to points and the label at the
				 * top left is changed
				 */
				if (bullet.getBoundsInParent().intersects(enemy1.getBoundsInParent())) {
					enemy1.setVisible(false);
					enemy1.setLayoutX(2000);
					enemy1.setLayoutY(2000);

					bullet.setVisible(false);
					bullet.setLayoutX(2000);

					points += 100;
					score.setText("Score: " + points);

				}
				if (bullet.getBoundsInParent().intersects(enemy2.getBoundsInParent())) {
					enemy2.setVisible(false);
					enemy2.setLayoutX(2000);
					enemy2.setLayoutY(2000);

					bullet.setVisible(false);
					bullet.setLayoutX(2000);

					points += 100;
					score.setText("Score: " + points);

				}
				if (bullet.getBoundsInParent().intersects(enemy3.getBoundsInParent())) {
					enemy3.setVisible(false);
					enemy3.setLayoutX(2000);
					enemy3.setLayoutY(2000);

					bullet.setVisible(false);
					bullet.setLayoutX(2000);

					points += 100;
					score.setText("Score: " + points);

				}

				// If the points == 300, and level is 1 (killed all enemies in the 1st level),
				// go to transition slide
				// increase the speed, meaning the bullet travels slower
				if (points == 300 && level == 1) {
					speed = 3;
					transition = 1;
					transition();
					timeline.stop();
					anchorPane.getChildren().remove(player);

				}

				// Same as code above, explanation above
				if (level >= 2) {
					if (bullet.getBoundsInParent().intersects(enemy4.getBoundsInParent())) {
						enemy4.setVisible(false);
						enemy4.setLayoutX(2000);
						enemy4.setLayoutY(2000);

						bullet.setVisible(false);
						bullet.setLayoutX(2000);

						points += 100;
						score.setText("Score: " + points);

					}
					if (bullet.getBoundsInParent().intersects(enemy5.getBoundsInParent())) {
						enemy5.setVisible(false);
						enemy5.setLayoutX(2000);
						enemy5.setLayoutY(2000);

						bullet.setVisible(false);
						bullet.setLayoutX(2000);

						points += 100;
						score.setText("Score: " + points);

					}
					// If the points == 800, and level is 2 (killed all enemies in the 2nd level),
					// go to transition slide
					// increase the speed, meaning the bullet travels slower
					if (level == 2 && points == 800) {
						speed = 17;
						transition = 1;
						transition();
						timeline.stop();
					}
				}

				// same as above
				if (level == 3) {
					if (bullet.getBoundsInParent().intersects(enemy6.getBoundsInParent())) {
						enemy6.setVisible(false);
						enemy6.setLayoutX(2000);
						enemy6.setLayoutY(2000);

						bullet.setVisible(false);
						bullet.setLayoutX(2000);

						points += 100;
						score.setText("Score: " + points);

					}
					if (bullet.getBoundsInParent().intersects(enemy7.getBoundsInParent())) {
						enemy7.setVisible(false);
						enemy7.setLayoutX(2000);
						enemy7.setLayoutY(2000);

						bullet.setVisible(false);
						bullet.setLayoutX(2000);

						points += 100;
						score.setText("Score: " + points);

					}

					// if you kill all the enemies in the third slide, win is set to 1
					if (level == 3 && points == 1500) {

						win = 1;

					}
					// if win is equal to 1, switch scenes and go to the win scene
					if (win == 1) {
						win();
						timeline.stop();
					}
				}

				// if the bullet hits the shield, move away the bullet and make it invisible
				int b = bullets.size() - 1;
				if (bullet.getBoundsInParent().intersects(shield.getBoundsInParent())) {
					bullets.get(b).setVisible(false);
					bullets.get(b).setTranslateX(2000);
					bullets.get(b).setTranslateY(2000);

					bullet.setVisible(false);
					bullet.setLayoutX(2000);

				}
			}
		});

		timeline.getKeyFrames().add(kf);
		timeline.play();

	}

	/*
	 * Transision screen, seen after everytime the player kills all the enemies in
	 * the level
	 */
	public static void transition() {
		transition = 1;

		if (win == 2) {
			lose();
		}
		Group root = new Group();
		Scene scene2 = new Scene(root, 750, 750, Color.SALMON);

		/*
		 * Image of the transition page
		 */
		// Creating an image
		Image tr = new Image("transition.png");

		// Setting the image view
		ImageView tr1 = new ImageView(tr);

		// Setting the position of the image
		tr1.setX(0);
		tr1.setY(0);

		// setting the fit height and width of the image view
		tr1.setFitHeight(750);
		tr1.setFitWidth(750);

		// button that takes you to the next level/wave
		Button click = new Button("Go To Next Wave!");
		click.setLayoutX(300);
		click.setLayoutY(500);
		click.setOnMouseClicked(e -> {

			// if the level == 1, then go to level 2, if level == 2 and points == 800, go to
			// level 3
			if (level == 1) {
				transition = 0;
				level2();
			}
			if (level == 2 && points == 800) {
				transition = 0;
				level3();
			}

		});
		stage.setScene(scene2);

		root.getChildren().add(tr1);
		root.getChildren().add(click);
	}

	/*
	 * Level 2 of the game
	 */
	public static void level2() {

		// setting level to 2, x = 0, and increasing the enemy bullet speed
		level = 2;
		x = 0;
		espeed = 7;
		win = 0;

		bullets = new ArrayList();

		anchorPane = new AnchorPane();
		Scene scene = new Scene(anchorPane, 750, 750);
		stage.setScene(scene);

		// Setting up player
		player.setLayoutX(350);
		player.setLayoutY(700);
		player.setTranslateX(0);

		// setting all the enemies and player back to being visible
		enemy1.setVisible(true);
		enemy2.setVisible(true);
		enemy3.setVisible(true);
		player.setVisible(true);

		// adding the enemies to this new scene
		enemy1 = new Rectangle(50, 50, Color.VIOLET);
		enemy1.setLayoutX(250);
		enemy1.setLayoutY(200);

		// Move this enemy side to side by 300x (same as above)
		TranslateTransition em = new TranslateTransition(Duration.millis(2000), enemy1);
		em.setByX(300);
		em.setCycleCount(Animation.INDEFINITE);
		em.setAutoReverse(true);
		em.play();

		// Adding all the enemies (essentially the same code as above)
		enemy2 = new Rectangle(50, 50, Color.VIOLET);
		enemy2.setLayoutX(500);
		enemy2.setLayoutY(300);

		enemy3 = new Rectangle(50, 50, Color.VIOLET);
		enemy3.setLayoutX(200);
		enemy3.setLayoutY(300);

		enemy4 = new Rectangle(50, 50, Color.VIOLET);
		enemy4.setLayoutX(0);
		enemy4.setLayoutY(100);
		TranslateTransition em4 = new TranslateTransition(Duration.millis(2000), enemy4);
		em4.setByX(700);
		em4.setCycleCount(Animation.INDEFINITE);
		em4.setAutoReverse(true);
		em4.play();

		enemy5 = new Rectangle(50, 50, Color.VIOLET);
		enemy5.setLayoutX(300);
		enemy5.setLayoutY(50);

		// Setting up shield
		shield = new Rectangle(100, 10, Color.CHOCOLATE);
		shield.setLayoutX(330);
		shield.setLayoutY(625);
		anchorPane.getChildren().add(shield);

		// Score label
		score = new Label("Score: " + points);
		score.setMinWidth(50);
		score.setMinHeight(50);
		score.setFont(new Font("Arial", 20));
		score.setTextFill(Color.web("#000000"));

		// event handler for pressed keys
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

			// if you press the right key, and the player is not at the edge, move 10 to the
			// right.
			if (event.getCode() == KeyCode.RIGHT) {
				if (x == 350) {

				}

				if (x < 350) {
					x += 10;
					player.setTranslateX(player.getX() + x);
				}
			}

			// if you press the left key, and the player is not at the edge, move 10 to the
			// left.
			if (event.getCode() == KeyCode.LEFT) {
				if (x < -350) {

				}
				if (x > -350) {
					x -= 10;
					player.setTranslateX(player.getX() + x);
				}
			}

			// If you press space, the bullet shoots
			if (event.getCode() == KeyCode.SPACE) {
				// add the rectangle
				bullets.add(new Rectangle(5, 10, Color.ORANGE));
				int i = bullets.size() - 1;
				// set the layout to the top of the player, and the middle of the player
				bullets.get(i).setLayoutX(player.getBoundsInParent().getMinX() + 25);
				bullets.get(i).setLayoutY(700);
				// add them to the anchorPane and go to animate bullets void
				anchorPane.getChildren().add(bullets.get(i));
				animateBullet(bullets.get(i));
			}

			// When you press the right arrow, all the odd numbered enemies shoot at you
			int eb = ebullets.size() - 1;
			if (event.getCode() == KeyCode.RIGHT) {

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy1.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy1.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy3.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy3.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy5.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy5.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

			}

			// When you press the left arrow, all the even numbered enemies shoot at you
			if (event.getCode() == KeyCode.LEFT) {

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy2.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy2.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy4.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy4.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));
			}

		});

		anchorPane.getChildren().add(score);
		anchorPane.getChildren().add(enemy1);
		anchorPane.getChildren().add(enemy2);
		anchorPane.getChildren().add(enemy3);
		anchorPane.getChildren().add(enemy4);
		anchorPane.getChildren().add(enemy5);
		anchorPane.getChildren().add(player);

	}

	/*
	 * level 3 of game
	 */
	public static void level3() {

		// setting level to 3, x = 0, and increasing the enemy bullet speed
		level = 3;
		x = 0;
		espeed = 7;
		win = 0;

		bullets = new ArrayList();

		anchorPane = new AnchorPane();
		Scene scene = new Scene(anchorPane, 750, 750);
		stage.setScene(scene);

		// Setting up player
		player.setLayoutX(350);
		player.setLayoutY(700);
		player.setTranslateX(0);

		// setting all the enemies and player back to being visible
		enemy1.setVisible(true);
		enemy2.setVisible(true);
		enemy3.setVisible(true);
		player.setVisible(true);

		// adding the enemies to this new scene
		enemy1 = new Rectangle(50, 50, Color.GREEN);
		enemy1.setLayoutX(250);
		enemy1.setLayoutY(200);

		// Move this enemy side to side by 300x (same as above)
		TranslateTransition em = new TranslateTransition(Duration.millis(2000), enemy1);
		em.setByX(300);
		em.setCycleCount(Animation.INDEFINITE);
		em.setAutoReverse(true);
		em.play();

		// Adding all the enemies (essentially the same code as above)
		enemy2 = new Rectangle(50, 50, Color.GREEN);
		enemy2.setLayoutX(500);
		enemy2.setLayoutY(300);

		enemy3 = new Rectangle(50, 50, Color.GREEN);
		enemy3.setLayoutX(200);
		enemy3.setLayoutY(300);

		enemy4 = new Rectangle(50, 50, Color.GREEN);
		enemy4.setLayoutX(0);
		enemy4.setLayoutY(100);
		TranslateTransition em4 = new TranslateTransition(Duration.millis(2000), enemy4);
		em4.setByX(700);
		em4.setCycleCount(Animation.INDEFINITE);
		em4.setAutoReverse(true);
		em4.play();

		enemy5 = new Rectangle(50, 50, Color.GREEN);
		enemy5.setLayoutX(300);
		enemy5.setLayoutY(50);

		// randomizing the x layout for the 2 enemies
		enemy6 = new Rectangle(50, 50, Color.FORESTGREEN);
		double z = Math.random();
		double ex6 = 0;
		if (z < 0.75) {
			ex6 = (z * 1000);
		}
		if (z >= 0.75) {
			ex6 = (z - 0.25) * 1000;
		}

		enemy6.setLayoutX(ex6);
		enemy6.setLayoutY(50);

		enemy7 = new Rectangle(50, 50, Color.FORESTGREEN);
		double z2 = Math.random();
		double ex7 = 0;
		if (z2 < 0.75) {
			ex7 = (z2 * 1000);
		}
		if (z2 >= 0.75) {
			ex7 = (z2 - 0.25) * 1000;
		}
		enemy7.setLayoutX(ex7);
		enemy7.setLayoutY(50);

		// Setting up shield
		shield = new Rectangle(100, 10, Color.CHOCOLATE);
		shield.setLayoutX(330);
		shield.setLayoutY(625);
		anchorPane.getChildren().add(shield);

		// Score label
		score = new Label("Score: " + points);
		score.setMinWidth(50);
		score.setMinHeight(50);
		score.setFont(new Font("Arial", 20));
		score.setTextFill(Color.web("#000000"));

		// event handler for pressed keys
		scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {

			// if you press the right key, and the player is not at the edge, move 10 to the
			// right.
			if (event.getCode() == KeyCode.RIGHT) {
				if (x == 350) {

				}

				if (x < 350) {
					x += 10;
					player.setTranslateX(player.getX() + x);
				}
			}

			// if you press the left key, and the player is not at the edge, move 10 to the
			// left.
			if (event.getCode() == KeyCode.LEFT) {
				if (x < -350) {

				}
				if (x > -350) {
					x -= 10;
					player.setTranslateX(player.getX() + x);
				}
			}

			// If you press space, the bullet shoots
			if (event.getCode() == KeyCode.SPACE) {
				// add the rectangle
				bullets.add(new Rectangle(5, 10, Color.ORANGE));
				int i = bullets.size() - 1;
				// set the layout to the top of the player, and the middle of the player
				bullets.get(i).setLayoutX(player.getBoundsInParent().getMinX() + 25);
				bullets.get(i).setLayoutY(700);
				// add them to the anchorPane and go to animate bullets void
				anchorPane.getChildren().add(bullets.get(i));
				animateBullet(bullets.get(i));
			}

			// When you press the right arrow, all the odd numbered enemies shoot at you
			int eb = ebullets.size() - 1;
			if (event.getCode() == KeyCode.RIGHT) {

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy1.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy1.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy3.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy3.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy5.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy5.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy7.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy7.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

			}

			// When you press the left arrow, all the even numbered enemies shoot at you
			if (event.getCode() == KeyCode.LEFT) {

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy2.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy2.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy4.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy4.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));

				ebullets.add(new Rectangle(5, 10, Color.SALMON));
				eb = ebullets.size() - 1;
				ebullets.get(eb).setLayoutX(enemy6.getBoundsInParent().getMinX() + 25);
				ebullets.get(eb).setLayoutY(enemy6.getBoundsInParent().getMaxY());
				anchorPane.getChildren().add(ebullets.get(eb));
				eanimateBullet(ebullets.get(eb));
			}

		});

		anchorPane.getChildren().add(score);
		anchorPane.getChildren().add(enemy1);
		anchorPane.getChildren().add(enemy2);
		anchorPane.getChildren().add(enemy3);
		anchorPane.getChildren().add(enemy4);
		anchorPane.getChildren().add(enemy5);
		anchorPane.getChildren().add(enemy6);
		anchorPane.getChildren().add(enemy7);
		anchorPane.getChildren().add(player);

	}

	/*
	 * Win method
	 */
	public static void win() {

		Group root = new Group();
		Scene scene2 = new Scene(root, 750, 750);
		anchorPane = new AnchorPane();
		Scene scene = new Scene(anchorPane, 750, 750);
		stage.setScene(scene);

		// Win screen, says you won and a button to play again or quit

		Label win = new Label("YOU WON!!");
		win.setLayoutX(250);
		win.setLayoutY(300);
		win.setMinWidth(100);
		win.setMinHeight(100);
		win.setFont(new Font("Arial", 65));
		win.setTextFill(Color.web("#000000"));

		Button startAgain = new Button("Click to Play Again");
		startAgain.setLayoutX(283);
		startAgain.setLayoutY(500);
		startAgain.setOnAction(e -> {
			menu();
		});

		Button exit = new Button("Click to Exit");
		exit.setLayoutX(283);
		exit.setLayoutY(600);
		exit.setOnAction(e -> {
			stage.close();
		});

		anchorPane.getChildren().add(win);
		anchorPane.getChildren().add(startAgain);
		anchorPane.getChildren().add(exit);

	}

	public static void lose() {
		win = 0;
		Group root = new Group();
		Scene scene2 = new Scene(root, 750, 750);
		anchorPane = new AnchorPane();
		Scene scene = new Scene(anchorPane, 750, 750);
		stage.setScene(scene);

		// Lose screen, says you lost, button to play again or quit
		Label win = new Label("YOU LOSE  :(");
		win.setLayoutX(250);
		win.setLayoutY(300);
		win.setMinWidth(100);
		win.setMinHeight(100);
		win.setFont(new Font("Arial", 65));
		win.setTextFill(Color.web("#000000"));

		Button startAgain = new Button("Click to Play Again");
		startAgain.setLayoutX(283);
		startAgain.setLayoutY(500);
		startAgain.setOnAction(e -> {
			menu();
		});

		Button exit = new Button("Click to Exit");
		exit.setLayoutX(283);
		exit.setLayoutY(600);
		exit.setOnAction(e -> {
			stage.close();
		});

		anchorPane.getChildren().add(win);
		anchorPane.getChildren().add(startAgain);
		anchorPane.getChildren().add(exit);
	}

	public static void main(String[] arg) {
		launch(arg);
	}
}