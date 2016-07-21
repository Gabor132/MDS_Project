package Constants;

import java.util.Map;

/**
 * This class will have all the path constants. Every resource will pe stored in
 * 'BattleAIMaven/src/main/resources' but upon build they are moved to
 * 'BattleAIMaven/target/classes'
 *
 * @author Dragos-Alexandru
 */
public abstract class PathConstants {

    public static final String RESOURCE_FOLDER_PATH = getResourceFolderPath();
    public static final String AI_TEMPLATE = RESOURCE_FOLDER_PATH + "AITemplate.txt";
    public static final String USER_SOURCES_FOLDER_PATH = RESOURCE_FOLDER_PATH + "User_Sources/";
    public static final String USER_SOURCES_INDEX_PATH = USER_SOURCES_FOLDER_PATH + "/sourcesIndex.index";
    public static final String TANK_BODY_SPRITE_PATH = RESOURCE_FOLDER_PATH + "tank_body.png";
    public static final String TANK_CANNON_SPRITE_PATH = RESOURCE_FOLDER_PATH + "tank_cannon.png";
    public static final String BULLET_SPRITE_PATH = RESOURCE_FOLDER_PATH + "bullet.png";
    
    private static String getResourceFolderPath(){
        
        String path = PathConstants.class.getResource("PathConstants.class").getPath();
        if(path.contains("classes")){
            path = path.replaceFirst("classes/Constants/PathConstants.class", "classes/");
        }else{
            path = path.replaceFirst("Constants/PathConstants.class", "");
        }
        path = path.replaceFirst("file:/", "");
        path = path.replaceFirst("!", "");
        Map<String,String> map = System.getenv();
        
        System.out.println(map);
        
//        if(map.get("NB_EXEC_MAVEN_PROCESS_UUID") == null){
//            path = "classes/";
//        }else{
//            System.out.println(map.get("NB_EXEC_MAVEN_PROCESS_UUID"));
//        }
        System.out.println("Default resource path: "+path);
        return path;
    }
}
