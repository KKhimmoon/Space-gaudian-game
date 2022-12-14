package screendrawing;


public class GameScreen {

	public class GameLogic extends Application {
		private Rocket player;
		private ArrayList<Shot> shots;
		private ArrayList<Enemy> enemys;
		private ArrayList<BombItem> Bombitems;
		private ArrayList<BulletItem> Bulletitems;
		public int countBomb;
		public static int BulletState;
		private int score;
		private static final Random RAND = new Random(); // private
		static final int WIDTH = 800;
		static final int HEIGHT = 600;
		static final int PLAYER_SIZE = 60;
		final int Maxbom = 10; 
		final int MaxShot = Maxbom*2;
		boolean gameOver = false;
		private GraphicsContext gc;
		private double mouseX;
		
		Enemy newEnemy() {
			return new Enemy(50+RAND.nextInt(WIDTH-100),0,PLAYER_SIZE);
			
		}
		BombItem newBomb() {
			return new BombItem(50 + RAND.nextInt(WIDTH-100),0,PLAYER_SIZE);
			
		}
		
		public static int distance(int x1,int y1,int x2,int y2) {
			return (int)Math.sqrt(Math.pow(x1-x2,2) + Math.pow((y1-y2), 2));
		}
		
		
		@Override
		public void start(Stage stage) throws Exception {
			// TODO Auto-generated method stub
			Canvas canvas = new Canvas(WIDTH,HEIGHT);
			gc = canvas.getGraphicsContext2D();
			Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),e-> run(gc)));
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
			canvas.setCursor(Cursor.MOVE);
			canvas.setOnMouseMoved(e-> mouseX = e.getX());
			canvas.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					if(shots.size() < MaxShot) {
						shots.add(player.shoot());
					}
					if(gameOver) {
						gameOver = false;
						setup();
					}
					
				}
			});
			setup();
			stage.setScene(new Scene(new StackPane(canvas)));
			stage.setTitle("Space Gaurdian");
			stage.show();
		}
		
		public void setup() {
			shots = new ArrayList<>();
			enemys = new ArrayList<>();
			Bombitems = new ArrayList<>();
			player = new Rocket(WIDTH/2,HEIGHT -PLAYER_SIZE,PLAYER_SIZE);
			score = 0;
			BulletState = 0;
			IntStream.range(0, Maxbom).mapToObj(i -> this.newEnemy());
		}
		
		private void run(GraphicsContext gc) {
			gc.setFill(Color.grayRgb(20));
			gc.fillRect(0, 0, WIDTH, HEIGHT);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setFont(Font.font(20));
			gc.setFill(Color.WHITE);
			gc.fillText("Score" + score,60,20);
			gc.setFont(Font.font(35));
			gc.setFill(Color.YELLOW);
			gc.fillText("Bomb" + countBomb,60,120);
			
			if(gameOver) {
				gc.setFont(Font.font(35));
				gc.setFill(Color.YELLOW);
				gc.fillText("Game Over" + score,WIDTH/2,HEIGHT/2.5);
			}
			if(RAND.nextInt(50) > 5) {
				enemys.add(newEnemy());
			}
			for(Enemy x:enemys) {
				x.draw(gc);
			}
			if(RAND.nextInt(50) > 5) {
				Bombitems.add(newBomb());
			}
			
			player.update();
			player.draw(gc);
			player.setPosX((int) mouseX);
			
//			player.update();
//			player.draw(gc);
//			player.setPosX((int) mouseX);
	//	
			

//			enemys.stream().peek(Rocket::update).peek(Rocket::draw(gc)).forEach(e -> {
//				if(player.colide(e) && !player.isExploding()) {
//					player.explode();
//				}
//			});
			for(int i = shots.size()-1;i>= 0;i--) {
				Shot shot = shots.get(i);
				if(shot.getPosY()< 0 || shot.isRemove) {
					shots.remove(i);
					continue;
				}
				shot.update();
				shot.draw(gc);
				for(Enemy x:enemys) {
					if(shot.colide(x) && !x.isExploding()) {
						score++;
						x.explode();
						shot.setRemove(true);
					}
				}
			}
			for(BombItem x:Bombitems) {
				x.draw(gc);
				if(player.colide(x) && !player.isExploding()) {
					x.explode();
					countBomb +=1;
					BulletState += 1;
				}
			}	
			for(int i = enemys.size()-1;i>= 0;i--) {
				if(enemys.get(i).isDestroyed()) {
					enemys.set(i, newEnemy());
				}
			}
			gameOver = player.isDestroyed();
//			if(RAND.nextInt(10)>2) {
//				univ.add(new Universe());
//			}
//			for(int i = 0;i< univ.size();i++) {
//				if(univ.get(i).posY > HEIGHT) {
//					univ.remove(i);
//				}
//			}
			
		}
		public static void main(String[] args) {
			launch(args);
		}
		

	}

	
	

}
