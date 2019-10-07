import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution_Correct {
    public static void main(String args[]) {
        long startTime = System.nanoTime();
        BufferedReader br = null;
        InputStreamReader isr = null;
        InputStream in = null;
        try {
            //System.setIn(new FileInputStream("C:\\Users\\SDSA\\Downloads\\wonho\\sample_input_shadow2.txt"));
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            int testCase = Integer.parseInt(br.readLine());
            double[] answers = new double[testCase];
            for(int t=0; t<testCase; t++) {
                ArrayList<Point> points = new ArrayList<Point>();
                int size = Integer.parseInt(br.readLine());
                String[] input = null;
                for(int i=0; i<size; i++) {
                    input = br.readLine().split(" ");
                    int x = Integer.parseInt(input[0]);
                    int y = Integer.parseInt(input[1]);

                    points.add(new Point(x,y));
                }

                input = br.readLine().split(" ");
                int vx = Integer.parseInt(input[0]);
                int vy = Integer.parseInt(input[1]);

                input = br.readLine().split(" ");
                int x1 = Integer.parseInt(input[0]);
                int y1 = Integer.parseInt(input[1]);
                int x2 = Integer.parseInt(input[2]);
                int y2 = Integer.parseInt(input[3]);

                Point defaultLineS = new Point(x1,y1);
                Point defaultLineE = new Point(x2,y2);
                Point[] twoPoints = new Point[2];
                for(Point p : points) {
                    Point endP = findEndPoint(p, vx, vy);
                    if(doIntersect(p, endP, defaultLineS, defaultLineE)) {
                        Point inter = lineLineIntersection(p, endP, defaultLineS, defaultLineE);
                        //System.out.println("inter : " + inter);
                        if(inter.x == Double.MAX_VALUE) continue;

                        if(twoPoints[0] == null) {
                            twoPoints[0] = inter;
                        } else if(twoPoints[1] == null) {
                            if(twoPoints[0].x > inter.x) {
                                twoPoints[1] = twoPoints[0];
                                twoPoints[0] = inter;
                            } else if(twoPoints[0].x == inter.x) {
                                if(twoPoints[0].y > inter.y) {
                                    twoPoints[1] = twoPoints[0];
                                    twoPoints[0] = inter;
                                } else {
                                    twoPoints[1] = inter;
                                }
                            } else {
                                twoPoints[1] = inter;
                            }

                        } else if(twoPoints[0].x > inter.x) {
                            twoPoints[0] = inter;
                        } else if(twoPoints[1].x < inter.x) {
                            twoPoints[1] = inter;
                        } else if(twoPoints[0].x == inter.x) {
                            if(twoPoints[0].y > inter.y) {
                                twoPoints[0] = inter;
                            } else if(twoPoints[1].x == inter.x) {
                                if(twoPoints[1].y < inter.y) {
                                    twoPoints[1] = inter;
                                }
                            }
                        }
                        //System.out.println("Cur 1: " + twoPoints[0]);
                        //System.out.println("Cur 2: " + twoPoints[1]);
                    }
                }

                if(twoPoints[0] != null && twoPoints[1] != null) {
                    answers[t] = Math.sqrt(
                            (twoPoints[0].x - twoPoints[1].x) * (twoPoints[0].x - twoPoints[1].x)
                                    + (twoPoints[0].y - twoPoints[1].y) * (twoPoints[0].y - twoPoints[1].y));
                }

            }



            //ANSWER PART
            for(int i=0; i<testCase; i++) {
                System.out.println("#" + (i+1) + " " + answers[i]);
            }
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            // System.out.println("totalTime : "+totalTime/1000000); // 1000 <- 1 seconds
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try { if(br!=null) br.close(); } catch(Exception e) {}
            try { if(isr!=null) isr.close(); } catch(Exception e) {}
            try { if(in!=null) in.close(); } catch(Exception e) {}
        }
    }

    public static Point findEndPoint(Point point, int vx, int vy) {
        int countX = 0;
        int countY = 0;
        if(vx > 0) {
            countX = (1000000 / vx) + 1;
        } else if(vx < 0) {
            countX = (-1000000 / vx) + 1;
        }

        if(vy > 0) {
            countY = (1000000 / vy) + 1;
        } else if(vy < 0) {
            countY = (-1000000 / vy) + 1;
        }

        int repeatCnt = countX > countY ? countX : countY;

        double x = point.x + (repeatCnt * vx);
        double y = point.y + (repeatCnt * vy);
        return new Point(x,y);
    }

    static boolean onSegment(Point p, Point q, Point r)  {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;

        return false;
    }

    static int orientation(Point p, Point q, Point r)  {
        double val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0;

        return (val > 0)? 1: 2;
    }

    // The main function that returns true if line segment 'p1q1'
    // and 'p2q2' intersect.
    static boolean doIntersect(Point p1, Point q1, Point p2, Point q2)
    {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
            return true;

        if (o1 == 0 && onSegment(p1, p2, q1)) return true;
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false;
    }


    static Point lineLineIntersection(Point A, Point B, Point C, Point D) {
        double a1 = B.y - A.y;
        double b1 = A.x - B.x;
        double c1 = a1*(A.x) + b1*(A.y);

        double a2 = D.y - C.y;
        double b2 = C.x - D.x;
        double c2 = a2*(C.x)+ b2*(C.y);

        double determinant = a1*b2 - a2*b1;

        if (determinant == 0) {
            return new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        }  else {
            double x = (b2*c1 - b1*c2)/determinant;
            double y = (a1*c2 - a2*c1)/determinant;
            return new Point(x, y);
        }
    }

    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
    }
}
