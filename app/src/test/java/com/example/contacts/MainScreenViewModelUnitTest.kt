package com.example.contacts

import com.example.contacts.constants.ContactConstants
import com.example.contacts.data.ContactRepository
import com.example.contacts.domain.ContactInteractor
import com.example.contacts.domain.ContactModel
import com.example.contacts.feature.mainscreen.ui.MainScreenViewModel
import com.example.contacts.utility.InstantExecutorExtension
import com.example.contacts.utility.anyExt
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExtendWith(InstantExecutorExtension::class)
class MainScreenViewModelUnitTest {

    private lateinit var repository: ContactRepository
    private lateinit var interactor: ContactInteractor
    private lateinit var viewModel: MainScreenViewModel
    private val listContacts: MutableList<ContactModel> = mutableListOf()

    @BeforeEach
    fun setup() {
        listContacts.addAll(ContactConstants.LIST_CONTACTS)

        repository = mock(ContactRepository::class.java)
        `when`(repository.getAll()).thenReturn(listContacts)

        interactor = ContactInteractor(repository)

        viewModel = MainScreenViewModel(interactor)
    }

    @Test
    fun `contact storage is not empty during initialization`() {
        assertEquals(ContactConstants.LIST_CONTACTS.size, viewModel.contacts.value?.size)
    }

    @Test
    fun `deleting an existing contact was successful`() {
        `when`(repository.delete(anyExt(ContactModel::class.java))).then {
            listContacts.remove(it.arguments[0])
        }

        viewModel.deleteContact(ContactConstants.CONTACT_1)

        assertEquals(ContactConstants.LIST_CONTACTS.size - 1, viewModel.contacts.value?.size)
    }

    @Test
    fun `deleting a non-existent contact was ignored successful`() {
        `when`(repository.delete(anyExt(ContactModel::class.java))).then {
            listContacts.remove(it.arguments[0])
        }

        viewModel.deleteContact(ContactConstants.CONTACT_UNKNOWN)

        assertEquals(ContactConstants.LIST_CONTACTS.size, viewModel.contacts.value?.size)
    }
}