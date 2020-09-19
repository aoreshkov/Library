package app.oreshkov.library.data.local

import app.oreshkov.library.model.Book
import app.oreshkov.library.util.EntityMapper
import javax.inject.Inject

class BookLocalMapper @Inject constructor() : EntityMapper<BookLocalEntity, Book> {

    override fun mapFromEntity(entity: BookLocalEntity): Book {
        return Book(
            id = entity.id,
            name = entity.name,
            description = entity.description
        )
    }

    override fun mapFromEntityList(entities: List<BookLocalEntity>): List<Book> {
        return entities.map { mapFromEntity(it) }
    }

    override fun mapToEntity(domainModel: Book): BookLocalEntity {
        return BookLocalEntity(
            id = domainModel.id,
            name = domainModel.name,
            description = domainModel.description
        )
    }
}