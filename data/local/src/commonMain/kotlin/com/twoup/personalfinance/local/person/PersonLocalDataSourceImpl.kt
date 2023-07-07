package com.twoup.personalfinance.local.person

import com.twoup.personalfinance.local.NoteDatabaseWrapper
import com.twoup.personalfinance.model.person.local.PersonEntity

class PersonLocalDataSourceImpl(databaseWrapper: NoteDatabaseWrapper): PersonLocalDataSource {

    private val database = databaseWrapper.instance
    private val dbQuery = database.personalFinanceDatabaseQueries

    override fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllPerson()
        }
    }

    override fun getAllPeople(): List<PersonEntity> {
        return dbQuery.selectAllPersonsInfo(::mapPeopleSelecting).executeAsList()
    }

    private fun mapPeopleSelecting(
        name: String?,
        height: String?,
        mass: String?,
        url: String?,
    ): PersonEntity {
        return PersonEntity(
            name,
            height,
            mass,
            url
        )
    }

    override fun createPeople(people: List<PersonEntity>) {
        dbQuery.transaction {
            people.forEach { person ->
                insertPerson(person)
            }
        }
    }

    private fun insertPerson(person: PersonEntity) {
        dbQuery.insertPerson(
            person.name,
            person.height,
            person.mass,
            person.url,
        )
    }
}