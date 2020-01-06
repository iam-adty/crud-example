<?php namespace App\Controllers;

use App\Entities\Barang as EntitiesBarang;
use App\Models\Barang as ModelsBarang;
use CodeIgniter\RESTful\ResourceController;
use Config\Services;

class Barang extends ResourceController {
    protected $modelName = ModelsBarang::class;
    protected $format = 'json';

    public function initController(\CodeIgniter\HTTP\RequestInterface $request, \CodeIgniter\HTTP\ResponseInterface $response, \Psr\Log\LoggerInterface $logger)
    {
        parent::initController($request, $response, $logger);

        $this->validator = Services::validation();
    }

    public function index()
    {
        $data = [];
        $this->model->where('deleted_at', null)->chunk(100, function($item) use (&$data) {
            $data[] = $item->toArray();
        });

        return $this->respond([
            'data' => $data,
        ]);
    }

    public function create()
    {
        if (!$this->validator->setRules([
            'nama' => 'required|string',
            'harga' => 'required|decimal'
        ])->run($this->request->getJSON(true))) {
            $error = '';
            foreach ($this->validator->getErrors() as $e) {
                if (empty($error)) {
                    $error = $e;
                } else {
                    $error .= ' ' . $e;
                }
            }

            return $this->failValidationError($error);
        }

        $barang = new EntitiesBarang($this->request->getJSON(true));
        
        $id = $this->model->insert($barang);

        if ($id == 0) {
            return $this->fail('Error Barang', 400, null, $this->model->db()->error()['message']);
        }

        $item = $this->model->find($id);

        return $this->respondCreated([
            'data' => $item->toArray()
        ]);
    }

    public function update($id = null)
    {
        if (!$this->validator->setRules([
            'id' => 'required|integer'
        ])->run([
            'id' => $id
        ])) {
            return $this->failValidationError($this->validator->getError('id'));
        }

        $item = $this->model->find($id);
        if (is_null($item)) {
            return $this->failNotFound();
        }

        $item->fill($this->request->getJSON(true));

        $update = $this->model->save($item);

        if ($update) {
            return $this->respond([
                'data' => $item->toArray()
            ]);
        } else {
            return $this->fail('Error Barang', 400, null, $this->model->db()->error()['message']);
        }
    }

    public function delete($id = null)
    {
        if (!$this->validator->setRules([
            'id' => 'required|integer'
        ])->run([
            'id' => $id
        ])) {
            return $this->failValidationError($this->validator->getError('id'));
        }

        $item = $this->model->find($id);
        if (is_null($item)) {
            return $this->failNotFound();
        }

        $delete = $this->model->delete($id);

        if ($delete) {
            return $this->respondDeleted();
        } else {
            return $this->fail('Error Barang', 400, null, $this->model->db()->error()['message']);
        }
    }
}