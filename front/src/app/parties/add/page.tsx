"use client"

import CreatePartyForm from "@/components/customs/Form/CreatePartyForm"
import { Spinner } from "@/components/customs/Utils/Spinner"
import { findAll } from "@/services/AddressService"
import { useQuery } from "@tanstack/react-query"

export default function CreatePartyPage() {
  const { data, error, isPending } = useQuery({ queryKey: ["addresses"], queryFn: findAll })

  if (isPending) return <Spinner />
  if (error) return <div>Error: {error.message}</div>

  return (
    <div className="m-10 pb-10 flex justify-center items-center">
      <CreatePartyForm addresses={data} />
    </div>
  )
}
