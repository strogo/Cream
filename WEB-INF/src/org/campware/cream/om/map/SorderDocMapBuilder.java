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
  * [Fri Jan 26 03:28:50 CET 2007]
  *
  */
public class SorderDocMapBuilder implements MapBuilder
{
    /**
     * The name of this class
     */
    public static final String CLASS_NAME =
        "org.campware.cream.om.map.SorderDocMapBuilder";


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

        dbMap.addTable("SORDER_DOC");
        TableMap tMap = dbMap.getTable("SORDER_DOC");

        tMap.setPrimaryKeyMethod(TableMap.NATIVE);


              tMap.addColumn("SORDER_DOC.DOC_ID", new Integer(0));
                    tMap.addColumn("SORDER_DOC.DOC_TYPE", new Integer(0));
                    tMap.addColumn("SORDER_DOC.DOC_CODE", new Integer(0));
                    tMap.addColumn("SORDER_DOC.STATUS", new Integer(0));
                    tMap.addColumn("SORDER_DOC.SUBJECT", "");
                    tMap.addColumn("SORDER_DOC.ISSUED_DATE", new Date());
                    tMap.addColumn("SORDER_DOC.SORDER_ID", new Integer(0));
                    tMap.addColumn("SORDER_DOC.CREATED", new Date());
          }
}