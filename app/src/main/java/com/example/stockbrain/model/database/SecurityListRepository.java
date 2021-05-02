package com.example.stockbrain.model.database;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.activeandroid.query.Select;
import com.example.stockbrain.model.businessobject.SecurityList;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SecurityListRepository extends AbstractRepository{

    protected SecurityListRepository(){}

        /**
         * Returns a list of elements found in the SecurityList table.
         */
        @Override
        public List<SecurityList> getAllItems(){
            return new Select().from(SecurityList.class).execute();
        }


    /**
         * Returns only one security list with provided id.
         * Returns null if tupel with given id does not exist
         */
        public SecurityList getSecurityListById(long id){
            return (SecurityList) super.getById(SecurityList.class, id);
        }

        public SecurityList getByName(String name) {
            return new Select().from(SecurityList.class).where("listName=?", new Object[]{name}).executeSingle();
        }





    }
