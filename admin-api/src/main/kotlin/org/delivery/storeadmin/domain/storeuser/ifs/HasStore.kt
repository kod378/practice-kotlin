package org.delivery.storeadmin.domain.storeuser.ifs

import org.delivery.storeadmin.domain.store.model.StoreResponse

interface HasStore {
    val storeResponse: StoreResponse?
}