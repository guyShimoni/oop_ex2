package Coords;



import Geom.Point3D;

public class MyCoords implements coords_converter{

	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		// TODO Auto-generated method stub
		Point3D temp= new Point3D(0,0,0);
		temp.add(gps);
		temp.add(local_vector_in_meter);
		return temp;
	}

	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		return gps0.distance3D(gps1);
	}

	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		Point3D temp= new Point3D(0,0,0);
		temp.add(gps1);
		double x= -gps0.x();
		double y= -gps0.y();
		double z= -gps0.z();
		temp.add(x,y,z);
		return temp;
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		double []aed=new double[3];
		aed[0]=gps0.north_angle(gps1);
		aed[1]=0;///change 
		aed[2]=gps0.distance3D(gps1);
		return aed;
	}

	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		// TODO Auto-generated method stub
		double x= p.x();
		double y= p.y();
		double z= p.z();
		boolean isX = -180<=x && x<=180;
		boolean isY = -90<=y && y<=90;
		boolean isZ = -450<=z ;
		return isX && isY && isZ;
	}

}
