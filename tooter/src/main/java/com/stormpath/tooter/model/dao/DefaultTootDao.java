/*
 * Copyright 2012 Stormpath, Inc. and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stormpath.tooter.model.dao;

import com.stormpath.tooter.model.Toot;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elder Crisostomo
 */
@Repository
public class DefaultTootDao extends BaseHibernateDao implements TootDao {

    @Override
    public List<Toot> getTootsByUserId(int custId) throws Exception {


        Criteria criteria = getSession().createCriteria(Toot.class);
        criteria.add(Restrictions.eq("customer.id", custId));
        List<?> list = new ArrayList<Object>();
        list.addAll(criteria.list());

        List<Toot> tootList = new ArrayList<Toot>(list.size());

        for (Object obj : list) {
            if (obj != null) {
                tootList.add((Toot) obj);
            }
        }

        return tootList;
    }

    @Override
    public Toot saveToot(Toot toot) throws Exception {

        save(toot);
        return toot;
    }

    @Override
    public void removeTootById(Integer tootId) throws Exception {
        deleteById(Toot.class, Integer.valueOf(tootId));
    }

    @Autowired
    public void init(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}
