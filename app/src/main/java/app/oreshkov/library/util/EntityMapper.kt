package app.oreshkov.library.util

interface EntityMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapFromEntityList(entities: List<Entity>): List<DomainModel>

    fun mapToEntity(domainModel: DomainModel): Entity

}