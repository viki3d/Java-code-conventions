/**
 * @author viki3d
 */
open module com.viki3d.javacodeconventions {
	requires org.slf4j;
	requires spring.web;
	requires spring.beans;
	requires spring.context;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.boot.test.autoconfigure;
	
	exports com.viki3d.javacodeconventions;
}