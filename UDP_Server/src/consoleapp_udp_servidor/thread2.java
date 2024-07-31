package consoleapp_udp_servidor;

public class thread2 extends Thread{
    public void run(){
        int count = 0;
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
        String car1 = cars[0];
        String car2 = cars[1];
        String car3 = cars[2];
        String car4 = cars[3];
        
        while (count <= 4) {
            System.out.println(car1 +"\n");
            System.out.println(car2 +"\n");
            System.out.println(car3 +"\n");
            System.out.println(car4 +"\n");
            // for(String i: cars){
            //     String message = i;
            //     System.out.println(message);
            // }
            // for (int i = 0; i< 10; i++){
            //     System.out.print("test for a thread: \n " + i);

            // }
            try {
                Thread.sleep(500);
                count++;
            } catch (Exception e) {
                System.err.println("theres was a error with the thread: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        thread2 th = new thread2();
        th.start();
        try {
            thread2.sleep(200);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}