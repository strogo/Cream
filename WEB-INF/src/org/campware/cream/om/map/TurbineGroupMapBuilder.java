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
public class TurbineGroupMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.TurbineGroupMapBuilder";


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

        dbMap.addTable("TURBINE_GROUP");
        TableMap tMap = dbMap.getTable("TURBINE_GROUP");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);

        tMap.setPrimaryKeyMethodInfo("TURBINE_GROUP_SEQ");

              tMap.addPrimaryKey("TURBINE_GROUP.GROUP_ID", new Integer(0));
                    tMap.addColumn("TURBINE_GROUP.GROUP_NAME", "");
          }
}