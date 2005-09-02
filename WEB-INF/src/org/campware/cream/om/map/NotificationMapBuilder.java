package org.campware.cream.om.map;

import java.util.Date;
import java.math.BigDecimal;

import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.map.MapBuilder;
import org.apache.torque.map.DatabaseMap;
import org.apache.torque.map.TableMap;

/**
  *  This class was autogenerated by Torque on:
  *
  * [Wed May 04 09:10:56 CEST 2005]
  *
  */
public class NotificationMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.NotificationMapBuilder";


    /**
     * The database map.
     */
    private DatabaseMap dbMap = null;

    /**
     * Tells us if this DatabaseMapBuilder is built so that we
     * don't have to re-build it every time.
     *
     * @return true if this DatabaseMapBuilder is built
     */
    public boolean isBuilt()
    {
        return (dbMap != null);
    }

    /**
     * Gets the databasemap this map builder built.
     *
     * @return the databasemap
     */
    public DatabaseMap getDatabaseMap()
    {
        return this.dbMap;
    }

    /**
     * The doBuild() method builds the DatabaseMap
     *
     * @throws TorqueException
     */
    public void doBuild() throws TorqueException
    {
        dbMap = Torque.getDatabaseMap("cream");

        dbMap.addTable("NOTIFICATION");
        TableMap tMap = dbMap.getTable("NOTIFICATION");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);

        tMap.setPrimaryKeyMethodInfo("NOTIFICATION_SEQ");

              tMap.addPrimaryKey("NOTIFICATION.NOTIFICATION_ID", new Integer(0));
                    tMap.addColumn("NOTIFICATION.NOTIFICATION_TYPE", new Integer(0));
                    tMap.addForeignKey(
                "NOTIFICATION.LANGUAGE_ID", new Integer(0) , "LANGUAGE" ,
                "LANGUAGE_ID");
                    tMap.addColumn("NOTIFICATION.EMAIL_FORMAT", new Integer(0));
                    tMap.addColumn("NOTIFICATION.SUBJECT", "");
                    tMap.addColumn("NOTIFICATION.BODY", "");
          }
}