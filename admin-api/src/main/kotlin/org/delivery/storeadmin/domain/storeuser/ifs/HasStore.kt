package org.delivery.storeadmin.domain.storeuser.ifs

import org.delivery.storeadmin.domain.store.model.StoreSimpleResponse

interface HasStore {
    val storeSimpleResponse: StoreSimpleResponse?
}